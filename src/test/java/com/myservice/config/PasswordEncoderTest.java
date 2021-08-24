package com.myservice.config;

import com.myservice.domain.login.LoginService;
import com.myservice.domain.member.Member;
import com.myservice.domain.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PasswordEncoderTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    void passwordEncode() {
        //given
        String rawPassword = "ab813f@csf#";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        Member member = Member.createUser("Tester", "Test", encodedPassword);
        memberService.save(member);

        //when
        Member member1 = loginService.login("Test", rawPassword);
        Member member2 = loginService.login("Test", encodedPassword);

        //then
        System.out.println("member1 = " + member1);
        System.out.println("member2 = " + member2);
        assertThat(member).isNotEqualTo(member1);
        assertThat(member).isEqualTo(member2);
    }
}