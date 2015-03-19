package sk.anext.msi.ui.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import sk.anext.msi.ui.validator.annotation.AuthorizationCodeFormat;

public class AuthorizationCodeFormatValidator implements ConstraintValidator<AuthorizationCodeFormat, String> {
    @Override
    public void initialize(AuthorizationCodeFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String authorizationCode, ConstraintValidatorContext context) {
        if (authorizationCode.matches("^[a-zA-Z0-9]+$")) { // iba alfanumericke
            return true;
        } else {
            return false;
        }
    }
}