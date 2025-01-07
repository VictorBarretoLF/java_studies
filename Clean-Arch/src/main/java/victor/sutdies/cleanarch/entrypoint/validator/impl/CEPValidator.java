package victor.sutdies.cleanarch.entrypoint.validator.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import victor.sutdies.cleanarch.entrypoint.validator.ValidCEP;

public class CEPValidator implements ConstraintValidator<ValidCEP, String> {

    @Override
    public boolean isValid(String cep, ConstraintValidatorContext context) {
        return !"".equals(cep) && cep.length() == 8;
    }

}
