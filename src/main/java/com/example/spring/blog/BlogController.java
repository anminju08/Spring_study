package com.example.spring.blog;
import com.example.spring.Item;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BlogController {
    private final BlogRepository blogRepository;

    @GetMapping("/list")
    public String get(Model model) {
        List<Blog> blogs = blogRepository.findAll();
        model.addAttribute("blogs", blogs);
        return "list";
    }

    //ItemController.java
    @GetMapping("/write")
    String write() {
        return "write.html";
    }

    @PostMapping("/add")
    String writePost(@RequestParam String title, @RequestParam Integer price) {
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setPrice(price);
        blogRepository.save(blog);
        return "redirect:/write";
    }

    //ItemController.java
    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) {
        Optional<Blog> result = blogRepository.findById(id);
        if (result.isPresent()){
            model.addAttribute("blog", result.get());
            System.out.println(result.get());
            return "detail.html";
        } else {
            return "redirect:/";
        }
    }

        @GetMapping("/edit/{id}")
        String edit(@PathVariable Long id, Model model) {
            Optional<Blog> result = blogRepository.findById(id);
            if (result.isPresent()) {
                model.addAttribute("data", result.get());
                return "edit.html";
            } else {
                return "redirect:/";
            }
        }

        @PostMapping("/edit/{id}")
        String editPost(@PathVariable Long id, @RequestParam String title, @RequestParam Integer price) {
            Optional<Blog> result = blogRepository.findById(id);
            if (result.isPresent()) {
                Blog blog = result.get();
                blog.setTitle(title);
                blog.setPrice(price);
                blogRepository.save(blog);
            }
            return "redirect:/detail/" + id;
        }

    @DeleteMapping("/blog/{id}")
    ResponseEntity<String> deleteItem(@PathVariable Long id) {
        blogRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

    @GetMapping("/test2")
    String test2() {
        var encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("qwer1234"));
        return "redirect:/list";
    }

    @GetMapping("/blog/login")
    public String login() {
        return "login.html";
    }
}



