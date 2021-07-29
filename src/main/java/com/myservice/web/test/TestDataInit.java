package com.myservice.web.test;

import com.myservice.domain.item.Item;
import com.myservice.domain.member.Grade;
import com.myservice.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

//@Component
@RequiredArgsConstructor
@Transactional
public class TestDataInit {

    private final EntityManager em;
//    private final ItemRepository itemRepository;
//    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
//        itemRepository.save(new Item("itemA", 10000, 10));
//        itemRepository.save(new Item("itemB", 20000, 20));
//        itemRepository.save(new Item("itemC", 15000, 15));

        Member member1 = new Member();
        member1.setLoginId("manager");
        member1.setPassword("manager");
        member1.setUsername("최한슬");
        member1.setGrade(Grade.MANAGER);

        Member member2 = new Member();
        member2.setLoginId("user");
        member2.setPassword("user");
        member2.setUsername("USER");
        member2.setGrade(Grade.USER);

        em.persist(member1);
        em.persist(member2);
    }

}