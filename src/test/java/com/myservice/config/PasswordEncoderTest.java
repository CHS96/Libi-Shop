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

    @Autowired private MemberService memberService;

    @Test
    @Transactional
    void passwordEncode() {
        //given
        String rawPassword = "ab813f@csf#";
        Member member = Member.createUser("Tester", "Test", rawPassword);
        memberService.save(member);

        //when
        Member findMember = memberService.findLoginId("Test").get();

        //then
        System.out.println("member = " + member);
        System.out.println("findMember = " + findMember);
        assertThat(member.getPassword()).isEqualTo(findMember.getPassword());
    }
}