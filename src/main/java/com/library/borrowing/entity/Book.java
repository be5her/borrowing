package com.library.borrowing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // use this annotation to declare your class as entitiy class
@Table(name = "Book") // creating student table
public class Book {
    // id, BookName, author, ISBN, Describtion, numberOfcopies

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // rule
    private Long id;

    @Column(name = "bookName", nullable = false)
    private String bookName;

    @Column(name = "Description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "numberOfCopies")
    private int numberOfCopies;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public String getIsbn() {
        return isbn;
    }

    public void addCopy() {
        this.numberOfCopies++;
    }

    public void removeCopy() {
        this.numberOfCopies--;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

}
