package com.myservice.domain.review;

import com.myservice.domain.item.Item;
import com.myservice.domain.member.Member;
import com.myservice.web.paging.Paging;
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
        return em.createQuery("select r from ItemReview r where r.item = :item order by r.item.id asc", ItemReview.class)
                .setParameter("item", item)
                .getResultList();
    }

    public List<ItemReview> findAllOfUser(Member user) {
        return em.createQuery("select i from ItemReview i where i.member = :user order by i.item.id asc", ItemReview.class)
                .setParameter("user", user)
                .getResultList();
    }

    public void remove(ItemReview review) {
        em.remove(review);
    }

    public List<ItemReview> findReviewsByPagingOfUser(Member user, int startIndex) {
        return em.createQuery("select i from ItemReview i where i.member = :user order by i.id asc", ItemReview.class)
                .setParameter("user", user)
                .setFirstResult(startIndex)
                .setMaxResults(Paging.MAX_SIZE)
                .getResultList();
    }

    /**
     * User Review 총 개수 count query
     */
    public Long findReviewsTotalSizeOfUser(Member user) {
        return (Long) em.createQuery("select count(*) from ItemReview i where i.member =: user")
                .setParameter("user", user)
                .getSingleResult();
    }

    public List<ItemReview> findReviewsByPagingOfItem(Item item, int startIndex) {
        return em.createQuery("select i from ItemReview i where i.item = :item order by i.id asc", ItemReview.class)
                .setParameter("item", item)
                .setFirstResult(startIndex)
                .setMaxResults(Paging.MAX_SIZE)
                .getResultList();
    }

    public Long findReviewsTotalSizeOfItem(Item item) {
        return (Long) em.createQuery("select count(*) from ItemReview i where i.item =: item")
                .setParameter("item", item)
                .getSingleResult();
    }
}