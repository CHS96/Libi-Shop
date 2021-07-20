package myservice.myservice.service;

import lombok.RequiredArgsConstructor;
import myservice.myservice.domain.Member;
import myservice.myservice.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> members() {
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            System.out.println("member = " + member);
        }
        return members;
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }
}
