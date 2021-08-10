package com.myservice.domain.cartline;

import com.myservice.domain.cart.Cart;
import com.myservice.domain.item.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Getter
@Setter
@Entity @ToString
public class CartLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_line_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    private CartLine() {}

    //==연관관계 메서드==//
    public void setCart(Cart cart) {
        this.cart = cart;
        cart.getCartLines().add(this);
    }

    public void setItem(Item item) {
        this.item = item;
    }

    //==생성 메서드==//
    public static CartLine createCareLine(Item item, int count) {
        CartLine cartLine = new CartLine();
        cartLine.setItem(item);
        cartLine.setCount(count);
        return cartLine;
    }

    //==비즈니스 로직==//

    /**
     * 주문 총 가격
     */
    public int getPrice() {
        return item.getPrice() * count;
    }

    /**
     * 상품 수량 추가
     */
    public void addCount(int count) {
        this.count += count;
    }
}
