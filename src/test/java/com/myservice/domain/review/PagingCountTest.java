package com.myservice.domain.review;

import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemRepository;
import com.myservice.domain.item.ItemType;
import com.myservice.domain.item.book.Book;
import com.myservice.domain.item.book.BookService;
import com.myservice.domain.member.Member;
import com.myservice.domain.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class PagingCountTest {

    @Autowired private ItemRepository itemRepository;
    @Autowired private ItemReviewService itemReviewService;
    @Autowired private BookService bookService;
    @Autowired private MemberService memberService;

    @Test
    @Transactional
    void pagingCountTest() {
        Book book = Book.createBook("bookA", 100, 100, "Abc");
        itemRepository.save(book);

        Member member = Member.createManager("test", "test", "123");
        memberService.save(member);

        for (int i = 1; i <= 10; ++i) {
            ItemReview itemReview1 = ItemReview.createItemReview("Hello " + i, "hi " + i, 3.8, member);
            itemReviewService.save(itemReview1, bookService.findItem(1L));
        }

        Long totalSize = itemReviewService.findReviewsTotalSizeOfUser(member);
        Assertions.assertThat(totalSize).isEqualTo(10);
    }
}