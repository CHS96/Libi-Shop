package com.myservice.domain.item;

import com.myservice.domain.cart.Cart;
import com.myservice.domain.member.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @NotEmpty
    private String itemName;

    @NotNull
    private Integer price;

    @NotNull
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    //==비즈니스 로직==//
    /**
     * 재고 감소
     */
    public void removeStock(int count) {
        setQuantity(getQuantity() - count);
    }

    /**
     * totalPrice
     */
    public int getTotalPrice(int count) {
        return getPrice() * count;
    }
}