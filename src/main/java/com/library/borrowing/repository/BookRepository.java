package com.library.borrowing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.borrowing.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
