package validation;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter
@Setter
public class BirthDate {
    @NotNull
    @Min(1)
    @Max(31)
    private int day;
    @Min(1)
    @Max(12)
    @NotNull
    private int month;
    @NotNull
    @Max(2000)
    @Min(1930)
    private int year;

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
