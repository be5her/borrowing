package com.library.borrowing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // use this annotation to declare your class as entitiy class
@Table(name = "Reader") // creating student table
public class Reader {
    // id, full name, emial, ***arraylist of borrowed books**

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // rule
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email")
    private String email;

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
