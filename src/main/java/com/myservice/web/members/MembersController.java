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

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MembersController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute MemberForm form) {
        return "member/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute MemberForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/addMemberForm";
        }

        //중복 아이디
        if (!validateDuplicateLoginId(form.getLoginId())) {
            bindingResult.reject("loginFail", "중복된 로그인 ID 입니다.");
            return "member/addMemberForm";
        }

        Member user = Member.createUser(form.getUsername(), form.getLoginId(), form.getPassword());
        memberService.save(user);
        return "redirect:/";
    }

    /**
     * 중복 아이디 방지
     */
    private boolean validateDuplicateLoginId(String loginId) {
        return memberService.findLoginId(loginId).isEmpty();
    }
}
