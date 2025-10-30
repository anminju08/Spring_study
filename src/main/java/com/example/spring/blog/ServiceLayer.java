package com.example.spring.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceLayer {

    private final BlogRepository blogRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public void addBlog(String title, Integer price) {
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setPrice(price);
        blogRepository.save(blog);
    }

    public Optional<Blog> getBlogDetail(Long id) {
        return blogRepository.findById(id);
    }
}