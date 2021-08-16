package com.myservice.domain.review;

import com.myservice.domain.item.Item;
import com.myservice.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemReviewRepository {

    private final EntityManager em;

    public Long save(ItemReview itemReview) {
        em.persist(itemReview);
        return itemReview.getId();
    }

    public ItemReview findOne(Long id) {
        return em.find(ItemReview.class, id);
    }

    public List<ItemReview> findAll(Item item) {
        return em.createQuery("select r from ItemReview r where r.item = :item", ItemReview.class)
                .setParameter("item", item)
                .getResultList();
    }

    public List<ItemReview> findAllOfUser(Member user) {
        return em.createQuery("select i from ItemReview i where i.member = :user", ItemReview.class)
                .setParameter("user", user)
                .getResultList();
    }
}
