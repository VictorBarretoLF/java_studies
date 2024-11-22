package com.springbootstudies.spring_boot_springdoc2.controller;

import com.springbootstudies.spring_boot_springdoc2.errors.ErrorResponseWithCode;
import com.springbootstudies.spring_boot_springdoc2.errors.ErrorResponseWithPath;
import com.springbootstudies.spring_boot_springdoc2.errors.exceptions.InvalidBookExeception;
import com.springbootstudies.spring_boot_springdoc2.errors.exceptions.ResourceNotFoundException;
import com.springbootstudies.spring_boot_springdoc2.model.Book;
import com.springbootstudies.spring_boot_springdoc2.dto.request.BookRequest;
import com.springbootstudies.spring_boot_springdoc2.service.BookService;
import com.springbootstudies.spring_boot_springdoc2.validation.BookValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/book/v1")
public class BookControllerImpl implements BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookValidator bookValidator;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        log.info("Who: User, What: Fetching book, When: {}, Where: BookControllerImpl.findById, Why: Fetch book by ID", LocalDateTime.now());
        return ResponseEntity.ok(bookService.findById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<Book> save(@Valid @RequestBody BookRequest bookRequest) {
        final var book = bookRequest.getBook();
        bookValidator.validateBook(book);
        final var savedBook = bookService.save(book);
        log.info("Who: User, What: Saving book, When: {}, Where: BookControllerImpl.save, Why: Save new book", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(savedBook));
    }

    @ExceptionHandler(InvalidBookExeception.class)
    private ResponseEntity<Map<String, Object>> handleInvalidBookException(InvalidBookExeception ex) {
        log.error("Who: User, What: Invalid book exception, When: {}, Where: BookControllerImpl.handleInvalidBookException, Why: Book validation failed", LocalDateTime.now());
        final var errors = ex.getViolations()
                .stream()
                .map(violation -> new ErrorResponseWithPath(violation.getPropertyPath().toString(), violation.getMessage()))
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("erros", errors));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseWithCode> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error("Who: User, What: Resource not found exception, When: {}, Where: BookControllerImpl.handleResourceNotFoundException, Why: Book not found", LocalDateTime.now());
        ErrorResponseWithCode errorResponse = new ErrorResponseWithCode();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setErrorCode(ex.getErrorCode().getCode());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
