package com.myservice.domain.cartline;

import com.myservice.domain.cart.Cart;
import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemRepository;
import com.myservice.domain.member.Member;
import com.myservice.domain.payment.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
     * payment All CartLines in Cart of User
     * delete CartLines And save Payment_Info
     */
    public void payment(Member user) {
        int totalPrice = user.getCart().getTotalPrice();
        Payment payment = new Payment();
        payment.setPrice(totalPrice);
        payment.setDateTime(LocalDateTime.now());
        user.getCart().getCartLines().clear();
    }

    public void createCart(Member user, Cart cart) {
        cartLineRepository.createCart(cart);
        cart.setMember(user);
    }
}
