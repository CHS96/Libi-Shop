package com.myservice.service;

import com.myservice.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import com.myservice.domain.Member;
import com.myservice.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository ;

    public List<Member> members() {
        return memberRepository.findAll();
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }
}
