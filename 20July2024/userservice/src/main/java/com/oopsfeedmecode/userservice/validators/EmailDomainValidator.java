package com.oopsfeedmecode.userservice.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailDomainValidator implements ConstraintValidator<ValidEmailDomain, String> {

    private static final String INVALID_DOMAIN = "@example.com";

    @Override
    public void initialize(ValidEmailDomain constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // or false, depending on your requirements
        }
        return !value.endsWith(INVALID_DOMAIN);
    }
}

