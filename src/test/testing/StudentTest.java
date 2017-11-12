package testing;

import org.junit.*;

import static org.junit.Assert.*;

import validation.*;

import javax.validation.*;
import java.math.BigDecimal;
import java.util.Set;

public class StudentTest {
    private Validator validator;

    @Before
    public void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void nameIsNull() {
        Student student = new Student(null, new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, new BigDecimal(3.8), false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void emailIsNull() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                null, "306434309343", "American",
                3, new BigDecimal(3.8), false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void emailNotCorrect() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj.com", "306434309343", "American",
                3, new BigDecimal(3.8), false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void birthDateIsValid() {
        Student student = new Student("Keone Madrid", new BirthDate(0, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, new BigDecimal(3.8), false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void nationalityIsNull() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", null,
                3, new BigDecimal(3.8), false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void courseIsOutOfRange() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                8, new BigDecimal(3.8), false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void classesIsNull() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, new BigDecimal(3.8), false);
        student.setClasses(null);
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void gpaIsNull() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, null, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());

    }

    @Test
    public void gpaOutOfRange() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, new BigDecimal(5.0), false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());

    }

    @Test
    public void scholarshipIsNegative() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, new BigDecimal(3.8), true);
        student.setScholarship(-900);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());

    }
}
