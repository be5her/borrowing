
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
    private String startTime;

    @Column
    private String endTime;

    @Column
    private String actualReturnTime;

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

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getActualReturnTime() {
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

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setActualReturnTime(String actualReturnTime) {
        this.actualReturnTime = actualReturnTime;
    }
}