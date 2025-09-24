package com.example.spring.blog;

import jakarta.persistence.*;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(length = 200)
    public String title;
    public Integer date;
    public Integer peopleCount;

    @Override
    public String toString() {
        return title + " " + date + " " + peopleCount;
    }
}