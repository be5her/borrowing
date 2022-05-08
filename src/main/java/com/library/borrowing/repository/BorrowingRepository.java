package com.library.borrowing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.borrowing.entity.Borrowing;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

}