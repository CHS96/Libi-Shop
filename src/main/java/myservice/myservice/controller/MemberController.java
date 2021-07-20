package myservice.myservice.controller;

import lombok.RequiredArgsConstructor;
import myservice.myservice.domain.Member;
import myservice.myservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

    @Autowired
    private final MemberService memberService;

    @ResponseBody
    @GetMapping
    public String members() {
        List<Member> members = memberService.members();
        return "ok";
    }

    @ResponseBody
    @GetMapping("/add")
    public String save(@RequestParam String name) {
        System.out.println("MemberController.save");
        Member member = new Member();
        member.setName(name);
        memberService.save(member);
        return "ok2";
    }
}
