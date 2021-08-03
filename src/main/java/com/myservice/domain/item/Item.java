package com.myservice.domain.item;

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
public class Item {

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