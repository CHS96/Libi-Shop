package com.myservice.domain.cartline;

import com.myservice.domain.cart.Cart;
import com.myservice.domain.item.Item;
import com.myservice.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CartLineRepository {

    private final EntityManager em;

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

    public void createCart(Cart cart) {
        em.persist(cart);
    }
}
