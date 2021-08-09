package com.myservice.domain.cartline;

import com.myservice.domain.cart.Cart;
import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemRepository;
import com.myservice.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CartLineService {

    private final ItemRepository itemRepository;
    private final CartLineRepository cartLineRepository;

    /**
     * add Item in Cart of User
     */
    public void addCart(Member user, Long itemId, int count) {
        //Item 재고 감소
        Item item = itemRepository.findById(itemId).get();
        item.removeStock(count);

        //User에게 동일한 상품이 존재하면 기존의 상품 수정
        CartLine cartLine = findCartLineWithCartAndItem(user.getCart(), item);
        if (cartLine != null) {
            cartLine.addCount(count);
            return;
        }
        //그렇지 않다면 새로운 CartLine 생성
        cartLine = CartLine.createCareLine(item, count);
        cartLine.setCart(user.getCart());
        cartLineRepository.saveCartLine(cartLine);
    }

    /**
     * edit Item in Cart of User
     */
    public void editCart(Member user, Long itemId, int count) {
        Item item = itemRepository.findById(itemId).get();
        CartLine cartLine = findCartLineWithCartAndItem(user.getCart(), item);

        //cartLine, item update
        int diff = count - cartLine.getCount();
        cartLine.setCount(count);
        item.removeStock(diff);
    }

    /**
     * find CarLine in Cart_Line Table by query(cart_Id, item_Id)
     */
    public CartLine findCartLineWithCartAndItem(Cart cart, Item item) {
        return cartLineRepository.findCartLineWithCartAndItem(cart, item);
    }

    /**
     * find All CartLine in Cart_Line
     */
    public List<CartLine> findAllCartLine(Member user) {
        return cartLineRepository.findAllCartLine(user);
    }

    /**
     * delete CartLine in Cart of User
     */
    public void deleteCartLine(Member user, Item item) {
        CartLine cartLine = findCartLineWithCartAndItem(user.getCart(), item);
        item.addStock(cartLine.getCount());
        cartLineRepository.deleteCartLine(cartLine);
    }

    /**
     * delete All CartLine in Cart of User
     */
    public void paymentCartLine(Member user) {
        List<CartLine> cartLines = findAllCartLine(user);
        for (CartLine cartLine : cartLines) {
            cartLineRepository.deleteCartLine(cartLine);
        }
    }

    public void createCart(Member user, Cart cart) {
        cartLineRepository.createCart(cart);
        cart.setMember(user);
    }
}
