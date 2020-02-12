package m.plywacz.exchangeratesapi.constraints;
/*
Author: BeGieU
Date: 12.02.2020
*/

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsName {
    String message() default "Field should contain only letters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

