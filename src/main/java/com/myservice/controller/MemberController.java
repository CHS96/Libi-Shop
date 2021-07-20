package com.myservice.controller;

import lombok.RequiredArgsConstructor;
import com.myservice.domain.Member;
import com.myservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    @Autowired
    private final MemberService memberService;

    @GetMapping
    public String members() {
        List<Member> members = memberService.members();
        return "ok";
    }

    @GetMapping("/add")
    public String save(@RequestParam String email, @RequestParam String password) {
        Member member = new Member(email, password);
        memberService.save(member);
        return "ok2";
    }
}
