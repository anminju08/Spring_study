package com.example.spring.blog;
import lombok.Setter;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    String detail(@PathVariable Integer id, Model model) {
        Optional<Blog> result = BlogRepository.findById();
        if (result.isPresent()){
            System.out.println(result.get());
            return "detail.html";
        } else {
            return "redirect:/";
        }
    }

}
