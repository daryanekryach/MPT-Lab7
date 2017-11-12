package validation;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
public class Student {
    @NotNull
    @Size(max = 45)
    private String name;
    @NotNull
    private String phone;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String nationality;
    @Valid
    private BirthDate dateOfBirth;
    @Min(1)
    @Max(6)
    private int course;
    @DecimalMax("4.0")
    @DecimalMin("1.0")
    private double GPA;
    @NotNull
    private ArrayList<String> classes;
    private boolean isOnScholarship = false;
    @Min(0)
    private int scholarship = 0;

    public Student() {
    }

    public Student(String name, BirthDate birthDate, String email, String phone, String nationality,
                   int course, double GPA, boolean isOnScholarship) {
        this.name = name;
        this.dateOfBirth = birthDate;
        this.email = email;
        this.phone = phone;
        this.nationality = nationality;
        this.course = course;
        this.GPA = GPA;
        this.isOnScholarship = isOnScholarship;
        classes = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder studentInfo = new StringBuilder();
        studentInfo.append("Student ").append(name).append(" was born ").append(dateOfBirth.toString()).
                append(" and is of ").append(nationality).append(" nationality. ").append(name).
                append(" is currently on the ").append(course).append(" course and has ").append(GPA).
                append(" GPA. \nStudent has ");
        if (isOnScholarship)
            studentInfo.append("scholarship of ").append(scholarship).append(". ");
        else
            studentInfo.append("no scholarship. ");
        studentInfo.append("This student is studying next subjects: ");
        for (String subject : classes) {
            if (classes.indexOf(subject) != classes.size() - 1) studentInfo.append(subject).append(", ");
            else studentInfo.append(subject).append(". \n");
        }
        studentInfo.append("Student can be contacted via phone - ").append(phone).append(" or email - ")
                .append(email).append(".");
        return studentInfo.toString();
    }

    public void addSubjects(String[] subjects) {
        for (String subejct : subjects)
            classes.add(subejct);
    }
}
