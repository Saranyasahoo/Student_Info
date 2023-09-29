package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "student")
@NoArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, message = "First name must be at least 3 characters long")
    private String firstName;

    @NotBlank
    @Size(min = 3, message = "Last name must be at least 3 characters long")
    private String lastName;
    private LocalDate dob;

    @Pattern(regexp = "[A-C]", message = "Valid values are A, B, or C")
    private String section;

    @Pattern(regexp = "[MF]", message = "Valid values are M or F")
    private String gender;

    @Min(value = 0, message = "Minimum marks should be 0")
    @Max(value = 100, message = "Maximum marks should be 100")
    @NotNull(message = "Marks 1 is mandatory")
    private int marks1;

    @Min(value = 0, message = "Minimum marks should be 0")
    @Max(value = 100, message = "Maximum marks should be 100")
    @NotNull(message = "Marks 2 is mandatory")
    private int marks2;

    @Min(value = 0, message = "Minimum marks should be 0")
    @Max(value = 100, message = "Maximum marks should be 100")
    @NotNull(message = "Marks 3 is mandatory")
    private int marks3;

    @Transient
    private int total;

    @Transient
    private double average;

    @Transient
    private String result;

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getMarks1() {
        return marks1;
    }

    public void setMarks1(int marks1) {
        this.marks1 = marks1;
    }

    public int getMarks2() {
        return marks2;
    }

    public void setMarks2(int marks2) {
        this.marks2 = marks2;
    }

    public int getMarks3() {
        return marks3;
    }

    public void setMarks3(int marks3) {
        this.marks3 = marks3;
    }

    public double getAverage() {
        return average;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
