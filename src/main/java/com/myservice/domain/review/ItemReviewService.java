package com.myservice.domain.review;

import com.myservice.domain.board.Board;
import com.myservice.domain.item.Item;
import com.myservice.domain.member.Member;
import com.myservice.web.user.review.ItemReviewUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ItemReviewService {

    private final ItemReviewRepository itemReviewRepository;

    @Transactional
    public Long save(ItemReview itemReview, Item item) {
        itemReview.setItem(item);
        return itemReviewRepository.save(itemReview);
    }

    @Transactional
    public ItemReview findOne(Long id, boolean flag) {
        ItemReview itemReview = itemReviewRepository.findOne(id);
        if (flag) itemReview.addViews(); //조회수 1증가
        return itemReview;
    }

    @Transactional(readOnly = true)
    public List<ItemReview> findAll(Item item) {
        return itemReviewRepository.findAll(item);
    }

    public List<ItemReview> findAllOfUser(Member user) {
        return itemReviewRepository.findAllOfUser(user);
    }

    public Long update(Long reviewId, ItemReviewUpdateForm form) {
        ItemReview itemReview = itemReviewRepository.findOne(reviewId);
        itemReview.updateItemReview(form);
        return reviewId;
    }

    public void remove(ItemReview review) {
        itemReviewRepository.remove(review);
    }
}
