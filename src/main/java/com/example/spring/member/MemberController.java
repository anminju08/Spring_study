package com.example.spring.member;

import com.example.spring.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication; // <-- 이거만 필요함
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
    public String register() {
        return "register.html";
    }
    @PostMapping("/member")
    public String addMember(
            String username,
            String password,
            String displayName
    ) {
        Member member = new Member();
        member.setUsername(username);
        String hash = bCryptPasswordEncoder.encode(password);
        member.setPassword(hash);
        member.setDisplayName(displayName);
        memberRepository.save(member);
        return "redirect:/list";
    }
    @GetMapping("/find/{username}")
    public String findMember(@PathVariable String username, Model model) {
        var name = memberRepository.findByUsername(username);
        model.addAttribute("members", name.get());
        System.out.println("name = " + name);
        return "find.html";
    }
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }


    @GetMapping("/my-page")
    public String myPage(Authentication auth) {
        System.out.println(auth);
        System.out.println(auth.getName()); //아이디출력가능
        System.out.println(auth.isAuthenticated()); //로그인여부 검사가능
        System.out.println(auth.getAuthorities().contains(new SimpleGrantedAuthority("일반유저")));
        return "my-page.html";
    }

    @GetMapping("/register")
    public String register(Authentication auth) {
        if(auth!=null){
            return "redirect:/list";
        }
        return "register";
    }
}


