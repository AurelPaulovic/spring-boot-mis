package sk.anext.msi.ui.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import sk.anext.msi.ui.validator.AuthorizationCodeFormatValidator;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER,
        ElementType.ANNOTATION_TYPE })
@Constraint(validatedBy = AuthorizationCodeFormatValidator.class)
public @interface AuthorizationCodeFormat {
        String message() default "Autorizačný kód musí byť 6-10 miestny alfanumerický reťazec.";

    Class<?>[] groups() default {}; 

    Class<?>[] payload() default { };
}