package com.myservice.domain.review;

import com.myservice.domain.item.Item;
import com.myservice.domain.member.Member;
import com.myservice.web.user.review.ItemReviewUpdateForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class ItemReview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String title;
    private String content;
    private Double star;
    private LocalDate dateTime;
    private Long views;

    private ItemReview() {
    }

    public static ItemReview createItemReview(String title, String content, Double star, Member user) {
        ItemReview itemReview = new ItemReview();
        itemReview.setTitle(title);
        itemReview.setContent(content);
        itemReview.setStar(star);
        itemReview.setMember(user);
        itemReview.setDateTime(LocalDate.now());
        itemReview.setViews(0L);
        return itemReview;
    }

    public void addViews() {
        this.views++;
    }

    public void updateItemReview(ItemReviewUpdateForm form) {
        this.setTitle(form.getTitle());
        this.setContent(form.getContent());
        this.setStar(form.getStar());
        this.setDateTime(LocalDate.now());
    }
}