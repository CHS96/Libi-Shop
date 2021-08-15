package com.myservice.domain.cart;

import com.myservice.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class CartRepository {

    private final EntityManager em;

    public Long save(Cart cart) {
        em.persist(cart);
        return cart.getId();
    }

    public Cart findCart(Member user) {
        return em.createQuery("select c from Cart c where c.member = :user", Cart.class)
                .setParameter("user", user)
                .getSingleResult();
    }
}
