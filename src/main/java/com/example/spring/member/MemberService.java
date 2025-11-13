package com.example.spring.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member register(String username, String password, String displayName) {
        if (username.length() < 8 || password.length() < 8) {
            throw new IllegalArgumentException("아이디와 비밀번호는 8자리 이상이어야 합니다.");
        }

        if (memberRepository.findByUsername(username).isPresent()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }

        Member member = new Member();
        var encoder = new BCryptPasswordEncoder();
        member.setUsername(username);
        member.setPassword(encoder.encode(password));
        member.setDisplayName(displayName);
        return memberRepository.save(member);
    }
}