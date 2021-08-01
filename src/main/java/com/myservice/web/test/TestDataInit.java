package com.myservice.web.test;

import com.myservice.domain.item.ItemService;
import com.myservice.domain.member.Manager;
import com.myservice.domain.member.MemberService;
import com.myservice.domain.member.User;
import com.myservice.web.manager.items.book.BookSaveForm;
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

    private final ItemService itemService;
    private final MemberService memberService;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemService.save(BookSaveForm.createBookSaveForm("Spring", 25000, 30, "Libi"));
        itemService.save(BookSaveForm.createBookSaveForm("Java in Java", 23000, 18, "Hi"));
        itemService.save(BookSaveForm.createBookSaveForm("Hello World", 15000, 15, "Hello"));

        memberService.save(Manager.createManager("최한슬", "manager", "manager"));
        memberService.save(User.createUser("USER", "user", "user"));
    }

}