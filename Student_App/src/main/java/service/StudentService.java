package service;

import entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import repositories.StudentRepo;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student saveStudent(Student student) {
        // Calculate total, average, and result here
        student.setTotal(student.getMarks1() + student.getMarks2() + student.getMarks3());
        student.setAverage(student.getTotal() / 3.0);

        if (student.getAverage() >= 60) {
            student.setResult("Pass");
        } else {
            student.setResult("Fail");
        }

        return studentRepo.save(student);
    }

    public void calculateAndSetResults(Student student, BindingResult bindingResult) {

        // Age validation
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(student.getDob(), currentDate).getYears();
        if (age <= 15 || age > 20) {
            throw new IllegalArgumentException("Age must be between 16 and 20 years.");
        }
        if (bindingResult.hasErrors()) {
            return;
        }

        if (student.getMarks1() < 35 || student.getMarks2() < 35 || student.getMarks3() < 35) {
            bindingResult.reject("marks", "Minimum marks for each subject is 35");
            return;
        }
    }
}
