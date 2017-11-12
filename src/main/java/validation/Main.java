package validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Metrics.start();
        ArrayList<Student> students = createStudents();
        if (validateStudents(students))
            printStudentsInfo(students);
        else System.out.println("sorry, one there seems to be a problem with validating one of students' info");
        Metrics.stop();
        Metrics.getAllMetrics();
    }

    public static boolean printStudentsInfo(ArrayList<Student> students) {
        if (validateStudents(students)) {
            for (Student student : students)
                System.out.println(student.toString() + "\n");
        }
        return true;
    }

    public static ArrayList<Student> createStudents() {
        ArrayList<Student> students = new ArrayList<>();
        Student student1 = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, 3.8, false);
        student1.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        students.add(student1);

        Student student2 = new Student("Sorah Yang", new BirthDate(7, 10, 1990),
                "gsdsfsdj@dhfkj.com", "306434533209", "Korean",
                6, 4.0, true);
        student2.setScholarship(2500);
        student2.addSubjects(new String[]{"Design", "Business", "Economics", "History"});
        students.add(student2);

        Student student3 = new Student("Akanen Mioshi", new BirthDate(15, 8, 1994),
                "gsdj@dhfkj.com", "306434309343", "Japanese",
                5, 3.9, false);
        student3.addSubjects(new String[]{"Design", "Literature", "Writing", "Chinese"});
        students.add(student3);
        return students;
    }

    public static boolean validateStudents(ArrayList<Student> students) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        boolean isValid = true;
        for (Student student : students) {
            Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
            if (constraintViolations.size() >= 1) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    private Main() throws InstantiationException
    {
        throw new InstantiationException("Instances of this type are forbidden.");
    }
}
