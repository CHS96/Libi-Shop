package com.myservice.web.manager.members;

import com.myservice.domain.member.Member;
import com.myservice.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/manager/members")
@RequiredArgsConstructor
public class ManagerMembersController {

    private final MemberRepository memberRepository;

    private final String VIEW_PATH = "member/manager/members/";

    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return VIEW_PATH + "members";
    }

    @GetMapping("{memberId}")
    public String member(@PathVariable Long memberId, Model model) {
        Optional<Member> member = memberRepository.findById(memberId);
        model.addAttribute("member", member.get());
        return VIEW_PATH + "member";
    }
}
