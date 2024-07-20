package com.oopsfeedmecode.userservice.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

public class CustomEmailValidator implements ConstraintValidator<CustomEmail, String> {

    private final EmailValidator emailValidator = new EmailValidator();

    @Override
    public void initialize(CustomEmail constraintAnnotation) {
        // Initialization code if needed
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        // If email is present, validate it. Otherwise, skip validation.
        if (email == null || email.isEmpty()) {
            return true; // Email is not provided, so skip validation
        }
        return emailValidator.isValid(email, context);
    }
}


