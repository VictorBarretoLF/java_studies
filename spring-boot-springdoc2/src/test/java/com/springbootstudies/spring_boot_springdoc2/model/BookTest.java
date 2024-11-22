package com.springbootstudies.spring_boot_springdoc2.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Set;

class BookTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void givenValidParams_whenCreateNewBook_thenInstatiateNewBook() {
        final var id = 1L;
        final var author = "Author";
        final var title = "Title";
        final var actualBook = new Book();

        actualBook.setId(id);
        actualBook.setTitle(title);
        actualBook.setAuthor(author);

        Assertions.assertNotNull(actualBook);
        Assertions.assertEquals(id, actualBook.getId());
        Assertions.assertEquals(author, actualBook.getAuthor());
        Assertions.assertEquals(title, actualBook.getTitle());
    }

    @Test
    void givenBlankTitle_whenCreateNewBook_thenCaptureConstraintViolations() {
        final var id = 1L;
        final var author = "Author";
        final var title = "";
        final var actualBook = new Book();

        actualBook.setId(id);
        actualBook.setTitle(title);
        actualBook.setAuthor(author);

        Set<ConstraintViolation<Book>> violations = validator.validate(actualBook);
        final var propertyPath = "title";
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals(propertyPath)));
    }

    @Test
    void givenBlankAuthor_whenCreateNewBook_thenCaptureConstraintViolations() {
        final var id = 1L;
        final var author = "";
        final var title = "Title";
        final var actualBook = new Book();

        actualBook.setId(id);
        actualBook.setTitle(title);
        actualBook.setAuthor(author);

        Set<ConstraintViolation<Book>> violations = validator.validate(actualBook);
        final var propertyPath = "author";
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals(propertyPath)));
    }

    @Test
    void givenTitleExceedingMaxLength_whenCreateNewBook_thenCaptureConstraintViolations() {
        final var id = 1L;
        final var author = "Author";
        final var title = "This title is way too long to be valid";
        final var actualBook = new Book();

        actualBook.setId(id);
        actualBook.setTitle(title);
        actualBook.setAuthor(author);

        Set<ConstraintViolation<Book>> violations = validator.validate(actualBook);
        final var propertyPath = "title";
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals(propertyPath)));
    }

    @Test
    void givenAuthorExceedingMaxLength_whenCreateNewBook_thenCaptureConstraintViolations() {
        final var id = 1L;
        final var author = "This author name is way too long to be valid";
        final var title = "Title";
        final var actualBook = new Book();

        actualBook.setId(id);
        actualBook.setTitle(title);
        actualBook.setAuthor(author);

        Set<ConstraintViolation<Book>> violations = validator.validate(actualBook);
        final var propertyPath = "author";
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals(propertyPath)));
    }

}