package com.springbootstudies.spring_boot_springdoc2.integratios.controller;

import com.springbootstudies.spring_boot_springdoc2.errors.ErrorCode;
import com.springbootstudies.spring_boot_springdoc2.errors.ErrorResponseWithCode;
import com.springbootstudies.spring_boot_springdoc2.model.Book;
import com.springbootstudies.spring_boot_springdoc2.dto.request.BookRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void givenValidBookRequest_whenSave_thenReturnCreatedBook() {
        // Arrange
        final var url = "/book/v1";
        final var bookRequest = new BookRequest();
        final var book = new Book();
        final var bookTitle = "Effective Java Book";
        final var bookAuthor = "Joshua Bloch";
        book.setTitle(bookTitle);
        book.setAuthor(bookAuthor);
        bookRequest.setBook(book);

        // Act
        final var response = restTemplate.postForEntity(url, bookRequest, Book.class);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(bookTitle, response.getBody().getTitle());
        assertEquals(bookAuthor, response.getBody().getAuthor());
    }

    @Test
    @Order(2)
    void givenValidId_whenFindById_thenReturnBook() {
        // Arrange
        final var bookId = 1L;
        final var bookTitle = "Effective Java Book";
        final var bookAuthor = "Joshua Bloch";
        final var url = "/book/v1/" + bookId;

        // Act
        ResponseEntity<Book> response = restTemplate.getForEntity(url, Book.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(bookTitle, response.getBody().getTitle());
        assertEquals(bookAuthor, response.getBody().getAuthor());
    }

    @Test
    @Order(3)
    void givenNonExistentId_whenFindById_thenReturnNotFound() {
        // Arrange
        final var nonExistentId = 999L;
        final var expectedErrorMessage = "Book with id " + nonExistentId + " not found";
        final var expectedErrorCode = ErrorCode.BOOK_NOT_FOUND.getCode();
        final var url = "/book/v1/" + nonExistentId;

        // Act
        ResponseEntity<ErrorResponseWithCode> response = restTemplate.getForEntity(url, ErrorResponseWithCode.class);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedErrorMessage, response.getBody().getMessage());
        assertEquals(expectedErrorCode, response.getBody().getErrorCode());
    }

    @Test
    void givenInvalidBookRequest_whenSave_thenReturnBadRequestWithErrors() {
        // Arrange
        final var url = "/book/v1";
        final var bookRequest = new BookRequest();
        final var book = new Book();
        final var invalidTitle = "";
        final var invalidAuthor = "This author name is way too long to be valid according to the constraints";
        book.setTitle(invalidTitle);
        book.setAuthor(invalidAuthor);
        bookRequest.setBook(book);

        // Act
        final var response = restTemplate.postForEntity(url, bookRequest, Map.class);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, ((List<?>) response.getBody().get("erros")).size());
    }

}
