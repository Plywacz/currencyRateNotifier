package m.plywacz.exchangeratesapi.constraints;
/*
Author: BeGieU
Date: 12.02.2020
*/

import org.springframework.util.Assert;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {
    @Override public void initialize(Currency constraintAnnotation) {
    }

    @Override public boolean isValid(String value, ConstraintValidatorContext context) {
        return Objects.nonNull(value) &&
                switch (value) { //java 12 switch
                    case "EUR", "USD", "GBP", "CHF", "JPY" -> true;
                    default -> false;
                };
    }


}
