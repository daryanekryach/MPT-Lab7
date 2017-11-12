package testing;

import org.junit.*;

import static org.junit.Assert.*;

import validation.*;
import java.util.ArrayList;

public class MainTest {
    @Test
    public void validateStudentTest() {
        ArrayList<Student> students = new ArrayList<>();
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, 3.8, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        students.add(student);
        assertTrue(Main.validateStudents(students));
    }
}
