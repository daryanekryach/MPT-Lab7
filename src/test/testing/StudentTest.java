package testing;

import org.junit.*;

import static org.junit.Assert.*;

import validation.*;

import javax.validation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Set;

public class StudentTest {
    private Validator validator;

    @Before
    public void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    //region Validation tests region
    @Test
    public void nameIsNull() {
        Student student = new Student(null, new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, 3.8, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void nameIsTooLong() {
        Student student = new Student("Louis George Maurice Adolphe Roche Albert Abel Antonio Long",
                new BirthDate(3, 4, 1996), "gsdj@dhfkj.com", "306434309343",
                "American", 3, 3.8, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void emailIsNull() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                null, "306434309343", "American",
                3, 3.8, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void emailNotCorrect() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj.com", "306434309343", "American",
                3, 3.8, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void phoneNotNull() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", null, "American",
                3, 3.8, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void birthDateIsValid() {
        Student student = new Student("Keone Madrid", new BirthDate(0, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, 3.8, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void nationalityIsNull() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", null,
                3, 3.8, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void courseIsLowerThanAllowed() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                0, 3.8, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void courseIsGreaterThanAllowed() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                8, 3.8, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void classesAreNull() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, 3.8, false);
        student.setClasses(null);
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void gpaIsLowerThanAllowed() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, 0, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void gpaIsGreaterThanAllowed() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, 6, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void scholarshipIsNegative() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, 3.8, true);
        student.setScholarship(-900);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        assertEquals(1, constraintViolations.size());

    }
    //endregion

    //region Properties tests region
    @Test
    public void setName() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        String name = "Virginia Evans";
        student.setName(name);
        final Field field = student.getClass().getDeclaredField("name");
        field.setAccessible(true);
        assertEquals(field.get(student), name);
    }

    @Test
    public void getDay() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        final Field field = student.getClass().getDeclaredField("name");
        field.setAccessible(true);
        String name = "Virginia Evans";
        field.set(student, name);
        assertEquals(student.getName(), name);
    }

    @Test
    public void setEmail() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        String email = "email@test.com";
        student.setEmail(email);
        final Field field = student.getClass().getDeclaredField("email");
        field.setAccessible(true);
        assertEquals(field.get(student), email);
    }

    @Test
    public void getEmail() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        final Field field = student.getClass().getDeclaredField("email");
        field.setAccessible(true);
        String email = "email@test.com";
        field.set(student, email);
        assertEquals(student.getEmail(), email);
    }

    @Test
    public void setPhone() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        String phone = "+380677852539";
        student.setPhone(phone);
        final Field field = student.getClass().getDeclaredField("phone");
        field.setAccessible(true);
        assertEquals(field.get(student), phone);
    }

    @Test
    public void getPhone() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        final Field field = student.getClass().getDeclaredField("phone");
        field.setAccessible(true);
        String phone = "+380677852939";
        field.set(student, phone);
        assertEquals(student.getPhone(), phone);
    }

    @Test
    public void setNationality() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        String nationality = "American";
        student.setNationality(nationality);
        final Field field = student.getClass().getDeclaredField("nationality");
        field.setAccessible(true);
        assertEquals(field.get(student), nationality);
    }

    @Test
    public void getNationality() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        final Field field = student.getClass().getDeclaredField("nationality");
        field.setAccessible(true);
        String nationality = "American";
        field.set(student, nationality);
        assertEquals(student.getNationality(), nationality);
    }

    @Test
    public void setCourse() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        int course = 2;
        student.setCourse(course);
        final Field field = student.getClass().getDeclaredField("course");
        field.setAccessible(true);
        assertEquals(field.get(student), course);
    }

    @Test
    public void getCourse() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        final Field field = student.getClass().getDeclaredField("course");
        field.setAccessible(true);
        int course = 5;
        field.set(student, course);
        assertEquals(student.getCourse(), course);
    }

    @Test
    public void setGPA() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        double GPA = 4.0;
        student.setGPA(GPA);
        final Field field = student.getClass().getDeclaredField("GPA");
        field.setAccessible(true);
        assertEquals(field.get(student), GPA);
    }

    @Test
    public void getGPA() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        final Field field = student.getClass().getDeclaredField("GPA");
        field.setAccessible(true);
        double GPA = 4.0;
        field.set(student, GPA);
        assertEquals(student.getGPA(), GPA,0);
    }

    @Test
    public void setScholarship() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        int scholarship = 2000;
        student.setScholarship(scholarship);
        final Field field = student.getClass().getDeclaredField("scholarship");
        field.setAccessible(true);
        assertEquals(field.get(student), scholarship);
    }

    @Test
    public void getScholarship() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        final Field field = student.getClass().getDeclaredField("scholarship");
        field.setAccessible(true);
        int scholarship = 2000;
        field.set(student, scholarship);
        assertEquals(student.getScholarship(), scholarship);
    }

    @Test
    public void setClasses() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        ArrayList<String> classes = new ArrayList<>();
        classes.add("Biology");
        classes.add("Economics");
        student.setClasses(classes);
        final Field field = student.getClass().getDeclaredField("classes");
        field.setAccessible(true);
        assertEquals(field.get(student), classes);
    }

    @Test
    public void getClasses() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        final Field field = student.getClass().getDeclaredField("classes");
        field.setAccessible(true);
        ArrayList<String> classes = new ArrayList<>();
        classes.add("Biology");
        classes.add("Economics");
        field.set(student, classes);
        assertEquals(student.getClasses(), classes);
    }

    @Test
    public void setBirthDate() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        BirthDate birthDate = new BirthDate(8, 4, 1996);
        student.setDateOfBirth(birthDate);
        final Field field = student.getClass().getDeclaredField("dateOfBirth");
        field.setAccessible(true);
        assertEquals(field.get(student), birthDate);
    }

    @Test
    public void getBirthDate() throws NoSuchFieldException, IllegalAccessException {
        final Student student = new Student();
        final Field field = student.getClass().getDeclaredField("dateOfBirth");
        field.setAccessible(true);
        BirthDate birthDate = new BirthDate(8, 4, 1996);
        field.set(student, birthDate);
        assertEquals(student.getDateOfBirth(), birthDate);
    }
    //endregion

    //region ToString method cases tests region
    @Test
    public void checkStudentWithScholarhipToString() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, 3.8, true);
        student.setScholarship(2500);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        String result = "Student Keone Madrid was born 3-4-1996 and is of American nationality. " +
                "Keone Madrid is currently on the 3 course and has 3.8 GPA. \n" +
                "Student has scholarship of 2500. This student is studying next subjects: " +
                "Biology, Literature, Chemistry, Journalism. \n" +
                "Student can be contacted via phone - 306434309343 or email - gsdj@dhfkj.com.";
        assertEquals(result, student.toString());
    }

    @Test
    public void checkStudentWithoutScholarhipToString() {
        Student student = new Student("Keone Madrid", new BirthDate(3, 4, 1996),
                "gsdj@dhfkj.com", "306434309343", "American",
                3, 3.8, false);
        student.addSubjects(new String[]{"Biology", "Literature", "Chemistry", "Journalism"});
        String result = "Student Keone Madrid was born 3-4-1996 and is of American nationality. " +
                "Keone Madrid is currently on the 3 course and has 3.8 GPA. \n" +
                "Student has no scholarship. This student is studying next subjects: " +
                "Biology, Literature, Chemistry, Journalism. \n" +
                "Student can be contacted via phone - 306434309343 or email - gsdj@dhfkj.com.";
        assertEquals(result, student.toString());
    }
    //endregion



}
