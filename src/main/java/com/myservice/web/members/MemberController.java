package com.myservice.web.members;

import com.myservice.domain.member.Member;
import com.myservice.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }

        log.info("member={}", member);
        //중복 아이디
        if (!validateDuplicateLoginId(member.getLoginId())) {
            bindingResult.reject("loginFail", "중복된 로그인 ID 입니다.");
            return "members/addMemberForm";
        }

        log.info("member={}", member);
        memberService.save(member);
        return "redirect:/";
    }

    /**
     * 중복 아이디 방지
     */
    private boolean validateDuplicateLoginId(String loginId) {
        return memberService.findLoginId(loginId).isEmpty();
    }
}
