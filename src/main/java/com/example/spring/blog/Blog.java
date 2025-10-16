package com.example.spring.blog;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Setter
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(length = 200)
    public String title;
    public Integer date;
    public Integer price;

    @Override
    public String toString() {
        return id + " " + title + " " + date;
    }

    public void setTitle(String title) {
        if (title.length() <= 255){
            this.title = title;
        }
    }

    public void setPrice(Integer price) {
        if (price <= 0){
            this.price = price;
        }
    }
}