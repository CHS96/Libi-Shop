package com.myservice.domain.review;

import com.myservice.domain.item.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ItemReview findOne(Long id) {
        ItemReview itemReview = itemReviewRepository.findOne(id);
        itemReview.addViews(); //조회수 1증가
        return itemReview;
    }

    @Transactional(readOnly = true)
    public List<ItemReview> findAll(Item item) {
        return itemReviewRepository.findAll(item);
    }

}
