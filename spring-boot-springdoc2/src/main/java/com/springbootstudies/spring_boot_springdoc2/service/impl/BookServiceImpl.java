package com.springbootstudies.spring_boot_springdoc2.service.impl;

import com.springbootstudies.spring_boot_springdoc2.errors.ErrorCode;
import com.springbootstudies.spring_boot_springdoc2.errors.exceptions.ResourceNotFoundException;
import com.springbootstudies.spring_boot_springdoc2.model.Book;
import com.springbootstudies.spring_boot_springdoc2.repository.BookRepository;
import com.springbootstudies.spring_boot_springdoc2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found", ErrorCode.BOOK_NOT_FOUND));
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

}
