package com.myservice.domain.cart;

import com.myservice.domain.item.Item;
import com.myservice.domain.member.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int price;
    private int count;

    //==연관관계 메서드==//
    public void setCart(Cart cart) {
        this.cart = cart;
        cart.getCartLines().add(this);
    }

    public void setItem(Item item) {
        this.item = item;
    }

    //==생성 메서드==//

    //==비즈니스 로직==//
}
