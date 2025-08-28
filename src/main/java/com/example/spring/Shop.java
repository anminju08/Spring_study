package com.example.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

@Controller
public class Shop {
    @GetMapping("/shop")
    String shopping(Model model) {
        model.addAttribute("item", "바지" );
        model.addAttribute("price", "15000" );
        model.addAttribute("count", "100" );
        return "index.html";
    }

}
