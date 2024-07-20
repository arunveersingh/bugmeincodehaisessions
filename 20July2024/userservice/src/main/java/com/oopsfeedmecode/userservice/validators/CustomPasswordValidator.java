package com.oopsfeedmecode.userservice.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CustomPasswordValidator implements ConstraintValidator<CustomPassword, String> {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

    @Override
    public void initialize(CustomPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isEmpty()) {
            return true; // Password is not provided, so skip validation
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}

