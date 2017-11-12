package testing;

import org.junit.*;

import static org.junit.Assert.*;

import javax.validation.*;
import validation.*;

import java.lang.reflect.Field;
import java.util.Set;

public class BirthDateTest {
    private Validator validator;

    @Before
    public void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    //region Validation tests region
    @Test
    public void dayIsLowerThanAllowed() {
        BirthDate birthDate = new BirthDate(0, 4, 1996);
        Set<ConstraintViolation<BirthDate>> constraintViolations = validator.validate(birthDate);
        assertEquals(1, constraintViolations.size());
    }
    @Test
    public void dayIsGreaterThanAllowed() {
        BirthDate birthDate = new BirthDate(32, 4, 1996);
        Set<ConstraintViolation<BirthDate>> constraintViolations = validator.validate(birthDate);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void monthIsLowerThanAllowed() {
        BirthDate birthDate = new BirthDate(5, 0, 1996);
        Set<ConstraintViolation<BirthDate>> constraintViolations = validator.validate(birthDate);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void monthIsGreaterThanAllowed() {
        BirthDate birthDate = new BirthDate(5, 13, 1996);
        Set<ConstraintViolation<BirthDate>> constraintViolations = validator.validate(birthDate);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void yearIsLowerThanAllowed() {
        BirthDate birthDate = new BirthDate(5, 12, 1000);
        Set<ConstraintViolation<BirthDate>> constraintViolations = validator.validate(birthDate);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void yearIsGreaterThanAllowed() {
        BirthDate birthDate = new BirthDate(5, 5, 3000);
        Set<ConstraintViolation<BirthDate>> constraintViolations = validator.validate(birthDate);
        assertEquals(1, constraintViolations.size());
    }
    //endregion

    //region Getters and setters tests region
    @Test
    public void setDay() throws NoSuchFieldException, IllegalAccessException {
        final BirthDate birthDate = new BirthDate();
        int day = 1;
        birthDate.setDay(day);
        final Field field = birthDate.getClass().getDeclaredField("day");
        field.setAccessible(true);
        assertEquals(field.get(birthDate), day);
    }

    @Test
    public void getDay() throws NoSuchFieldException, IllegalAccessException {
        final BirthDate birthDate = new BirthDate();
        final Field field = birthDate.getClass().getDeclaredField("day");
        field.setAccessible(true);
        int day = 1;
        field.set(birthDate, day);
        assertEquals(birthDate.getDay(), day);
    }

    @Test
    public void setMonth() throws NoSuchFieldException, IllegalAccessException {
        final BirthDate birthDate = new BirthDate();
        int month = 1;
        birthDate.setMonth(month);
        final Field field = birthDate.getClass().getDeclaredField("month");
        field.setAccessible(true);
        assertEquals(field.get(birthDate), month);
    }

    @Test
    public void getMonth() throws NoSuchFieldException, IllegalAccessException {
        final BirthDate birthDate = new BirthDate();
        final Field field = birthDate.getClass().getDeclaredField("month");
        field.setAccessible(true);
        int month = 1;
        field.set(birthDate, month);
        assertEquals(birthDate.getMonth(), month);
    }

    @Test
    public void setYear() throws NoSuchFieldException, IllegalAccessException {
        final BirthDate birthDate = new BirthDate();
        int year = 1998;
        birthDate.setYear(year);
        final Field field = birthDate.getClass().getDeclaredField("year");
        field.setAccessible(true);
        assertEquals(field.get(birthDate), year);
    }

    @Test
    public void getYear() throws NoSuchFieldException, IllegalAccessException {
        final BirthDate birthDate = new BirthDate();
        final Field field = birthDate.getClass().getDeclaredField("year");
        field.setAccessible(true);
        int year = 1998;
        field.set(birthDate, year);
        assertEquals(birthDate.getYear(), year);
    }
    //endregion

    @Test
    public void checkBirthDateToString() {
        BirthDate birthDate = new BirthDate(3, 4, 1996);
        String result = "3-4-1996";
        assertEquals(result, birthDate.toString());
    }
}
