package testing;

import org.junit.*;

import static org.junit.Assert.*;

import validation.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class MainTest {

    @Test
    public void exceptionThrowForInstanceTest() throws IllegalAccessException, InstantiationException {
        final Class<?> mainClass = Main.class;
        final Constructor<?> c = mainClass.getDeclaredConstructors()[0];
        c.setAccessible(true);

        Throwable targetException = null;
        try {
            c.newInstance((Object[])null);
        } catch (InvocationTargetException ite) {
            targetException = ite.getTargetException();
        }
        assertNotNull(targetException);
        assertEquals(targetException.getClass(), InstantiationException.class);
    }

    @Test
    public void validateStudentTrueTest() {
        ArrayList<Student> students = new ArrayList<>();
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, 3.8, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        students.add(student);
        assertTrue(Main.validateStudents(students));
    }

    @Test
    public void validateStudentFalseTest() {
        ArrayList<Student> students = new ArrayList<>();
        Student student = new Student("Keone Madrid", new BirthDate(0, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, 3.8, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        students.add(student);
        assertFalse(Main.validateStudents(students));
    }

    @Test
    public void printStudentsInfoTest(){
        ArrayList<Student> students = new ArrayList<>();
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, 3.8, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        students.add(student);
        assertTrue(Main.printStudentsInfo(students));
    }

}
