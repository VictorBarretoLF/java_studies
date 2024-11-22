package com.springbootstudies.spring_boot_springdoc2.validation;

import com.springbootstudies.spring_boot_springdoc2.errors.exceptions.InvalidBookExeception;
import com.springbootstudies.spring_boot_springdoc2.model.Book;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookValidator {

    private final Validator validator;

    public void validateBook(Book book) {
        validate(book);
    }

    private void validate(Object object) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            log.error("Invalid object: {}", object);
            throw new InvalidBookExeception(violations);
        }
    }

}
