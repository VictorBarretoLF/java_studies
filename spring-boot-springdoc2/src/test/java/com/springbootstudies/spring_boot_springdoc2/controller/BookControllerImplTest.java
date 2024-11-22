package com.springbootstudies.spring_boot_springdoc2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootstudies.spring_boot_springdoc2.dto.request.BookRequest;
import com.springbootstudies.spring_boot_springdoc2.errors.ErrorCode;
import com.springbootstudies.spring_boot_springdoc2.errors.exceptions.InvalidBookExeception;
import com.springbootstudies.spring_boot_springdoc2.errors.exceptions.ResourceNotFoundException;
import com.springbootstudies.spring_boot_springdoc2.model.Book;
import com.springbootstudies.spring_boot_springdoc2.service.BookService;
import com.springbootstudies.spring_boot_springdoc2.validation.BookValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @InjectMocks
    private BookControllerImpl bookController;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookValidator bookValidator;

    @Autowired
    private Validator validator;

    @Test
    void givenValidId_whenFindById_thenReturnBook() throws Exception {
        // Arrange
        final var bookUrl = "/book/v1/1";
        BDDMockito.given(bookService.findById(1L)).willReturn(new Book(1L, "Book 1", "Author 1"));

        // Act & Assert
        MockHttpServletResponse response = mockMvc.perform(
                get(bookUrl)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Book 1"))
                .andExpect(jsonPath("$.author").value("Author 1"))
                .andReturn().getResponse();
    }

    @Test
    void givenInvalidId_whenFindById_thenThrowResourceNotFoundException() throws Exception {
        // Arrange
        final var bookId = 999L;
        final var bookUrl = "/book/v1/" + bookId;
        final var errorMessage = String.format("Book with id %d not found", bookId);
        final var errorCode = ErrorCode.BOOK_NOT_FOUND.getCode();
        BDDMockito.given(bookService.findById(bookId)).willThrow(new ResourceNotFoundException(String.format(errorMessage), ErrorCode.BOOK_NOT_FOUND));

        // Act & Assert
        MockHttpServletResponse response = mockMvc.perform(
                get(bookUrl).contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(errorMessage))
                .andExpect(jsonPath("$.error_code").value(errorCode))
                .andReturn().getResponse();
    }

    @Test
    void givenValidBook_whenSave_thenReturnSavedBook() throws Exception {
        // Arrange
        final var book = new Book(1L, "Effective Java", "Joshua Bloch");
        final var bookUrl = "/book/v1";
        final var bookRequest = new BookRequest(book);
        BDDMockito.given(bookService.save(book)).willReturn(book);

        // Act & Assert
        MockHttpServletResponse response = mockMvc.perform(
                        post(bookUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(bookRequest))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Effective Java"))
                .andExpect(jsonPath("$.author").value("Joshua Bloch"))
                .andReturn().getResponse();
    }

    @Test
    void givenInvalidBookWithEmptyTitle_whenSave_thenThrowInvalidBookException() throws Exception {
        // Arrange
        final var bookUrl = "/book/v1";
        final var invalidBook = new Book(0L, "", "John Snow");
        final var path = "title";
        final var bookRequest = new BookRequest(invalidBook);
        Set<ConstraintViolation<Object>> violations = validator.validate(invalidBook);
        BDDMockito.willThrow(new InvalidBookExeception(violations)).given(bookValidator).validateBook(invalidBook);

        // Act & Assert
        MockHttpServletResponse response = mockMvc.perform(
                        post(bookUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(bookRequest))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erros").isArray())
                .andExpect(jsonPath("$.erros[0].message").isNotEmpty())
                .andExpect(jsonPath("$.erros[0].path").value(path))
                .andReturn().getResponse();
    }

    @Test
    void givenInvalidBookWithEmptyAuthor_whenSave_thenThrowInvalidBookException() throws Exception {
        // Arrange
        final var bookUrl = "/book/v1";
        final var invalidBook = new Book(0L, "Effective Java", "");
        final var path = "author";
        final var bookRequest = new BookRequest(invalidBook);
        Set<ConstraintViolation<Object>> violations = validator.validate(invalidBook);
        BDDMockito.willThrow(new InvalidBookExeception(violations)).given(bookValidator).validateBook(invalidBook);

        // Act & Assert
        MockHttpServletResponse response = mockMvc.perform(
                        post(bookUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(bookRequest))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erros").isArray())
                .andExpect(jsonPath("$.erros[0].message").isNotEmpty())
                .andExpect(jsonPath("$.erros[0].path").value(path))
                .andReturn().getResponse();
    }

    @Test
    void givenInvalidBookWithAllEmptyParams_whenSave_thenThrowInvalidBookException() throws Exception {
        // Arrange
        final var bookUrl = "/book/v1";
        final var invalidBook = new Book(0L, "", "");
        final var bookRequest = new BookRequest(invalidBook);
        Set<ConstraintViolation<Object>> violations = validator.validate(invalidBook);
        BDDMockito.willThrow(new InvalidBookExeception(violations)).given(bookValidator).validateBook(invalidBook);

        // Act & Assert
        MockHttpServletResponse response = mockMvc.perform(
                        post(bookUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(bookRequest))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erros").isArray())
                .andExpect(jsonPath("$.erros[0].message").isNotEmpty())
                .andExpect(jsonPath("$.erros[0].path").isNotEmpty())
                .andExpect(jsonPath("$.erros[1].message").isNotEmpty())
                .andExpect(jsonPath("$.erros[1].path").isNotEmpty())
                .andReturn().getResponse();
    }

    @Test
    void givenInvalidBookWithNullTitle_whenSave_thenThrowInvalidBookException() throws Exception {
        // Arrange
        final var bookUrl = "/book/v1";
        final var invalidBook = new Book(0L, null, "John Snow");
        final var bookRequest = new BookRequest(invalidBook);
        Set<ConstraintViolation<Object>> violations = validator.validate(invalidBook);
        BDDMockito.willThrow(new InvalidBookExeception(violations)).given(bookValidator).validateBook(invalidBook);

        // Act & Assert
        MockHttpServletResponse response = mockMvc.perform(
                        post(bookUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(bookRequest))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erros").isArray())
                .andExpect(jsonPath("$.erros[0].message").isNotEmpty())
                .andExpect(jsonPath("$.erros[0].path").isNotEmpty())
                .andReturn().getResponse();
    }

    @Test
    void givenInvalidBookWithNullAuthor_whenSave_thenThrowInvalidBookException() throws Exception {
        // Arrange
        final var bookUrl = "/book/v1";
        final var invalidBook = new Book(0L, "Effective Java", null);
        final var bookRequest = new BookRequest(invalidBook);
        Set<ConstraintViolation<Object>> violations = validator.validate(invalidBook);
        BDDMockito.willThrow(new InvalidBookExeception(violations)).given(bookValidator).validateBook(invalidBook);

        // Act & Assert
        MockHttpServletResponse response = mockMvc.perform(
                        post(bookUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(bookRequest))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erros").isArray())
                .andExpect(jsonPath("$.erros[0].message").isNotEmpty())
                .andExpect(jsonPath("$.erros[0].path").isNotEmpty())
                .andReturn().getResponse();
    }

    @Test
    void givenInvalidBookWithNullParams_whenSave_thenThrowInvalidBookException() throws Exception {
        // Arrange
        final var bookUrl = "/book/v1";
        final var invalidBook = new Book(0L, null, null);
        final var bookRequest = new BookRequest(invalidBook);
        Set<ConstraintViolation<Object>> violations = validator.validate(invalidBook);
        BDDMockito.willThrow(new InvalidBookExeception(violations)).given(bookValidator).validateBook(invalidBook);

        // Act & Assert
        MockHttpServletResponse response = mockMvc.perform(
                        post(bookUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(bookRequest))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erros").isArray())
                .andExpect(jsonPath("$.erros[0].message").isNotEmpty())
                .andExpect(jsonPath("$.erros[0].path").isNotEmpty())
                .andExpect(jsonPath("$.erros[1].message").isNotEmpty())
                .andExpect(jsonPath("$.erros[1].path").isNotEmpty())
                .andReturn().getResponse();
    }

}