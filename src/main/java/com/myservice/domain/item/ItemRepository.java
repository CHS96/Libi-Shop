package com.myservice.domain.item;

import com.myservice.domain.cart.Cart;
import com.myservice.domain.cart.CartLine;
import com.myservice.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
@Primary
public class ItemRepository {

    private final EntityManager em;

    public Long save(Item item) {
        em.persist(item);
        return item.getId();
    }

    public Optional<Item> findById(Long id) {
        Item item = em.find(Item.class, id);
        return Optional.ofNullable(item);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public void deleteItem(Long itemId) {
        Item item = findById(itemId).get();
        em.remove(item);
    }

    public void saveCartLine(CartLine cartLine) {
        em.persist(cartLine);
    }

    /**
     * Find CartLine in Cart_Line Table by cart_Id, item_Id
     */
    public CartLine findCartLineWithCartAndItem(Cart cart, Item item) {
        return em.createQuery("select c from CartLine c where c.cart = :cart and c.item = :item", CartLine.class)
                .setParameter("cart", cart)
                .setParameter("item", item)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    /**
     * Find All CartLine in Cart of User
     */
    public List<CartLine> findAllCartLine(Member user) {
        return em.createQuery("select c from CartLine c where c.cart = :cart", CartLine.class)
                .setParameter("cart", user.getCart())
                .getResultList();
    }

    /**
     * delete CartLine in Cart_Line Table by cart_Id, item_Id
     */
    public void deleteCartLine(CartLine cartLine) {
        em.remove(cartLine);
    }
}
