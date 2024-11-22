package com.springbootstudies.spring_boot_springdoc2.service.impl;

import com.springbootstudies.spring_boot_springdoc2.errors.ErrorCode;
import com.springbootstudies.spring_boot_springdoc2.errors.exceptions.ResourceNotFoundException;
import com.springbootstudies.spring_boot_springdoc2.model.Book;
import com.springbootstudies.spring_boot_springdoc2.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private Book book;

    @Captor
    private ArgumentCaptor<Book> bookArgumentCaptor;

    @Test
    void givenValidId_whenFindById_thenReturnBook() {
        // Arrange
        Long id = 1L;

        // Act
        BDDMockito.given(bookRepository.findById(id)).willReturn(Optional.of(book));
        Book book = bookService.findById(id);

        // Assert
        Assertions.assertDoesNotThrow(() -> bookService.findById(id));
        Assertions.assertNotNull(book);
    }

    @Test
    void givenInvalidId_whenFindById_thenThrowResourceNotFoundException() {
        // Arrange
        final var expectedErrorCode = ErrorCode.BOOK_NOT_FOUND;
        Long id = 999L;

        // Act
        BDDMockito.given(bookRepository.findById(id)).willReturn(Optional.empty());

        // Assert
        ResourceNotFoundException exception =
                Assertions.assertThrows(ResourceNotFoundException.class, () -> bookService.findById(id));
        Assertions.assertEquals(expectedErrorCode, exception.getErrorCode());
        Assertions.assertEquals(expectedErrorCode.getCode(), exception.getErrorCode().getCode());
    }

    @Test
    void givenBook_whenSave_thenReturnSavedBook() {
        // Arrange
        BDDMockito.given(bookRepository.save(book)).willReturn(book);

        // Act
        final var savedBook = bookService.save(book);

        // Assert
        BDDMockito.then(bookRepository).should().save(bookArgumentCaptor.capture());
        Book capturedBook = bookArgumentCaptor.getValue();
        Assertions.assertEquals(savedBook, capturedBook);
    }

}