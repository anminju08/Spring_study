package com.example.spring.blog;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BlogController {
    private final BlogRepository blogRepository;

    @GetMapping
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
        return "redirect:/";
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

}
