package com.springbootstudies.spring_boot_springdoc2.errors.exceptions;

import jakarta.validation.ConstraintViolation;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class InvalidBookExeception extends RuntimeException {
    private final Set<ConstraintViolation<Object>> violations;

    public InvalidBookExeception(Set<ConstraintViolation<Object>> violations) {
        super(getErrorMessage(violations));
        this.violations = violations;
    }

    private static String getErrorMessage(Set<ConstraintViolation<Object>> violations) {
        return "Validation errors: " +
                violations.stream()
                        .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                        .sorted()
                        .collect(Collectors.joining(", "));
    }

}
