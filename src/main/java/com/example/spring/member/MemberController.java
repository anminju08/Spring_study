package com.example.spring.member;

import com.example.spring.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberService memberService;

    // 회원가입 (POST)
    @PostMapping("/register")
    @ResponseBody
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String displayName
    ) {
        memberService.register(username, password, displayName);
        return "회원가입 성공!";
    }

    // 회원가입 페이지 (GET) - 로그인 시 list로 redirect
    @GetMapping("/register")
    public String register(Authentication auth) {
        if(auth != null){   // 로그인 상태면 회원가입 페이지 못 들어가게
            return "redirect:/list";
        }
        return "register.html";
    }

    // 회원 저장 (POST)
    @PostMapping("/member")
    public String addMember(String username, String password, String displayName) {
        Member member = new Member();
        member.setUsername(username);

        String hash = bCryptPasswordEncoder.encode(password);
        member.setPassword(hash);

        member.setDisplayName(displayName);
        memberRepository.save(member);

        return "redirect:/list";
    }

    // 회원 조회
    @GetMapping("/find/{username}")
    public String findMember(@PathVariable String username, Model model) {
        var name = memberRepository.findByUsername(username);
        model.addAttribute("members", name.get());
        System.out.println("name = " + name);
        return "find.html";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    // 마이페이지
    @GetMapping("/my-page")
    public String myPage(Authentication auth) {
        System.out.println(auth);
        System.out.println(auth.getName());
        System.out.println(auth.isAuthenticated());
        System.out.println(auth.getAuthorities().contains(new SimpleGrantedAuthority("일반유저")));
        return "my-page.html";
    }
}