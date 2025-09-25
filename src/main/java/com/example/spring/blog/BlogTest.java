package com.example.spring.blog;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class BlogTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private Integer age;
    private String name;

    private BlogTest(String age, String name) {
        this.age = Integer.parseInt(age)+1;
        this.name = name;
    }

    private void testAge(Integer age){
        if(age>=0 && age<100){
            this.age = age;
        }else{
            System.out.println("0~99살까지만 가능");
    }
        }

    private void oneaddAge() {
        if (this.age != null && this.age + 1 < 100) {
            this.age++;
        }
    }

}

