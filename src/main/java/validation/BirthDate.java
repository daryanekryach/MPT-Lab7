package validation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class BirthDate {
    @Min(1)
    @Max(31)
    private int day;
    @Min(1)
    @Max(12)
    private int month;
    @Max(2000)
    @Min(1930)
    private int year;

    public BirthDate(){}

    public BirthDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%s-%s-%s", day, month, year);
    }
}
