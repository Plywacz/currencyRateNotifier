package m.plywacz.exchangeratesapi.constraints;
/*
Author: BeGieU
Date: 12.02.2020
*/

import m.plywacz.exchangeratesapi.exceptions.IncorrectJsonInputException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.lang.Character.isDigit;

public class NameValidator implements ConstraintValidator<Name, String> {
    @Override
    public void initialize(Name constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return false;

        var result = true;

        var chars = value.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (!isSmallLetter(chars[i]) || isDigit(chars[i]))
                result = false;
        }
        return result && !value.isBlank() && isCapitalLetter(chars[0]) && !isDigit(chars[0]);

    }

    private boolean isSmallLetter(char letter) {
        return !Character.isUpperCase(letter);
    }

    private boolean isCapitalLetter(char letter) {
        return Character.isUpperCase(letter);
    }
}
