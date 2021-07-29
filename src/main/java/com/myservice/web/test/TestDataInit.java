package com.myservice.web.test;

import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemRepository;
import com.myservice.domain.member.Grade;
import com.myservice.domain.member.Member;
import com.myservice.domain.member.MemberRepository;
import com.myservice.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class TestDataInit {

    private final ItemRepository itemRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
        itemRepository.save(new Item("itemC", 15000, 15));

        Member member1 = new Member();
        member1.setLoginId("manager");
        member1.setPassword("manager");
        member1.setUsername("최한슬");
        member1.setGrade(Grade.MANAGER);

        Member member2 = new Member();
        member2.setLoginId("user");
        member2.setPassword("user");
        member2.setUsername("USER");

        //바로 memberRepository.save로 접근하면 현재 스레드에서 사용할 수 있는 EntityManager가 없다고 오류 발생
        memberRepository.save(member1);
        memberRepository.save(member2);
        //memberService.save -> memberRepository.save로 접근하면 정상적으로 작동
//        memberService.save(member1);
//        memberService.save(member2);
    }

}