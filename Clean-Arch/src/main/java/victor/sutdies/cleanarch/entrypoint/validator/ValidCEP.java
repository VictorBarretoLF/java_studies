package victor.sutdies.cleanarch.entrypoint.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import victor.sutdies.cleanarch.entrypoint.validator.impl.CEPValidator;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = CEPValidator.class)
@Target({ FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCEP {
    String message() default "CEP Inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
