package parkingspot.validation;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

@Slf4j
public class FutureDateStringValidator implements ConstraintValidator<FutureDateString, String> {

    @Override
    public void initialize(final FutureDateString constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String date, final ConstraintValidatorContext context) {
        try {
            return date != null && LocalDate.parse(date).isAfter(LocalDate.now());
        } catch (Exception ex) {
            log.warn("Date processing error: {}: {}", date, ex.getMessage());
            return false;
        }
    }
}
