
package com.library.borrowing.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//readerid, bookid, timrofborroing, timeofreturn, status
@Entity // use this annotation to declare your class as entitiy class
@Table(name = "Borrowing") // creating student table
public class Borrowing {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // rule
    private Long id;

    @Column
    private Long readerId;

    @Column
    private Long bookId;

    @Column
    private String status;

    @Column
    private Timestamp startTime;

    @Column
    private Timestamp endTime;

    @Column
    private Timestamp actualReturnTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReaderId() {
        return readerId;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public Timestamp getActualReturnTime() {
        return actualReturnTime;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setActualReturnTime(Timestamp actualReturnTime) {
        this.actualReturnTime = actualReturnTime;
    }
}