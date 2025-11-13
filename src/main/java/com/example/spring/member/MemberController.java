package com.example.spring.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    @ResponseBody
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String displayName) {

        memberService.register(username, password, displayName);
        return "회원가입 성공!";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }
}