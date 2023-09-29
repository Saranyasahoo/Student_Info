package controller;

import entity.Student;
import exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student, BindingResult bindingResult) {
        studentService.calculateAndSetResults(student, bindingResult);

        if (bindingResult.hasErrors()) {
            // For simplicity, just returning the error messages as a response
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        // If no validation errors, continue processing

        return ResponseEntity.ok("Student created successfully");
    }

    @PutMapping("/{id}/marks")
    public ResponseEntity<?> updateStudentMarks(
            @PathVariable Long id,
            @RequestParam Integer marks1,
            @RequestParam Integer marks2,
            @RequestParam Integer marks3) {

        SimpleJpaRepository<Student, Long> studentRepo = null;
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        student.setMarks1(marks1);
        student.setMarks2(marks2);
        student.setMarks3(marks3);

        BindingResult bindingResult = null;
        studentService.calculateAndSetResults(student, bindingResult);

        // Save the updated student record
        studentRepo.save(student);

        return ResponseEntity.ok("Student marks updated successfully");
    }
}
