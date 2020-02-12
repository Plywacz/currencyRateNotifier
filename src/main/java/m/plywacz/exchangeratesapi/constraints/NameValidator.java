package m.plywacz.exchangeratesapi.constraints;
/*
Author: BeGieU
Date: 12.02.2020
*/

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.lang.Character.isDigit;

public class NameValidator implements ConstraintValidator<IsName, String> {
    @Override
    public void initialize(IsName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        var chars = value.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (!isSmallLetter(chars[i])|| isDigit(chars[i]))
                return false;
        }
        return isCapitalLetter(chars[0])&& ! isDigit(chars[0]);
    }

    private boolean isSmallLetter(char letter) {
        return !Character.isUpperCase(letter);
    }

    private boolean isCapitalLetter(char letter) {
        return Character.isUpperCase(letter);
    }
}
