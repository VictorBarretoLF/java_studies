package com.springbootstudies.spring_boot_springdoc2.validation;

import com.springbootstudies.spring_boot_springdoc2.errors.exceptions.InvalidBookExeception;
import com.springbootstudies.spring_boot_springdoc2.model.Book;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BookValidatorTest {

    private static Validator validator;
    private static BookValidator bookValidator;

    @BeforeAll
    static void setUp() {
        // Instantiate a real Validator
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        // Inject the Validator into BookValidator
        bookValidator = new BookValidator(validator);
    }

    @Test
    void givenValidBook_whenValidate_thenDoesNotThrowException() {
        // Arrange
        Book book = new Book();
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");

        // Act and Assert
        Assertions.assertDoesNotThrow(() -> bookValidator.validateBook(book));
    }

    @Test
    void givenAnInvalidBook_whenValidate_thenShouldThrowException() {
        // Arrange
        Book book = new Book();

        // Act and Assert
        Assertions.assertThrows(InvalidBookExeception.class, () -> bookValidator.validateBook(book));
    }

    @Test
    void givenAnInvalidEmptyTitle_whenCallValidate_thenShouldThrowException() {
        // Arrange
        Book book = new Book();
        book.setTitle("");
        book.setAuthor("Joshua Bloch");

        // Act and Assert
        Assertions.assertThrows(InvalidBookExeception.class, () -> bookValidator.validateBook(book));
    }

    @Test
    void givenAnInvalidEmptyAuthor_whenCallValidate_thenShouldThrowException() {
        // Arrange
        Book book = new Book();
        book.setTitle("Effective Java");
        book.setAuthor("");

        // Act and Assert
        Assertions.assertThrows(InvalidBookExeception.class, () -> bookValidator.validateBook(book));
    }

    @Test
    void givenAnInvalidNullTitle_whenCallValidate_thenShouldThrowException() {
        // Arrange
        Book book = new Book();
        book.setTitle(null);
        book.setAuthor("Joshua Bloch");

        // Act and Assert
        Assertions.assertThrows(InvalidBookExeception.class, () -> bookValidator.validateBook(book));
    }

    @Test
    void givenAnInvalidNullAuthor_whenCallValidate_thenShouldThrowException() {
        // Arrange
        Book book = new Book();
        book.setTitle("Effective Java");
        book.setAuthor(null);

        // Act and Assert
        Assertions.assertThrows(InvalidBookExeception.class, () -> bookValidator.validateBook(book));
    }

    @Test
    void givenAnInvalidTitleLengthMoreThan20_whenCallValidate_thenShouldThrowException() {
        // Arrange
        Book book = new Book();
        book.setTitle("Effective Java Book by Joshua Bloch");
        book.setAuthor("Joshua Bloch");

        // Act and Assert
        Assertions.assertThrows(InvalidBookExeception.class, () -> bookValidator.validateBook(book));
    }

    @Test
    void givenAnInvalidAuthorLengthMoreThan20_whenCallValidate_thenShouldThrowException() {
        // Arrange
        Book book = new Book();
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch Author his name is too long");

        // Act and Assert
        Assertions.assertThrows(InvalidBookExeception.class, () -> bookValidator.validateBook(book));
    }

}