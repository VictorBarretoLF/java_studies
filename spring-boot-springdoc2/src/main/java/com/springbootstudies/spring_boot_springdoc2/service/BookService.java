package com.springbootstudies.spring_boot_springdoc2.service;

import com.springbootstudies.spring_boot_springdoc2.model.Book;

import java.util.Optional;

public interface BookService {

    public Book findById(Long id);
    public Book save(Book book);

}
