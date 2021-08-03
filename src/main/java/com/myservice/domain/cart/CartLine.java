package com.myservice.domain.cart;

import com.myservice.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CartLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_line_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    private CartLine() {
    }

    ;

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
}
