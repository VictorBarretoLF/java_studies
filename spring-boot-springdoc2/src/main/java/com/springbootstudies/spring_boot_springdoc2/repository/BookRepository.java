package com.springbootstudies.spring_boot_springdoc2.repository;

import com.springbootstudies.spring_boot_springdoc2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}