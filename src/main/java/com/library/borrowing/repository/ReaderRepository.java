package com.library.borrowing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.borrowing.entity.Reader;


public interface ReaderRepository extends JpaRepository<Reader, Long> {

}