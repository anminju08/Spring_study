package com.example.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class First {
    @GetMapping("/about")
    @ResponseBody
    String fishing() {
        return "피싱사이트이다.";
    }

    @GetMapping("/html")
    @ResponseBody
    String html() {
        return "<h4>반갑습니다.</h4>";
    }

    @GetMapping("/date")
    @ResponseBody
    String date() {
        return "<input type=\"date\" name=\"myDate\">반갑습니다.";
    }

    @GetMapping("/html1")

    String html1() {
        return "index.html";
    }
}
