package parkingspot.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class FutureDateValidator implements ConstraintValidator<FutureDate, LocalDate> {

    @Override
    public void initialize(final FutureDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(final LocalDate date, final ConstraintValidatorContext context) {
        return date != null && date.isAfter(LocalDate.now());
    }
}
