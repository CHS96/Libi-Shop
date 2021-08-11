package com.myservice.domain.item;

import com.myservice.web.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    //==생성 메서드==//
    public static Item createItem(String itemName, ItemType itemType, Integer price, Integer quantity) {
        Item item = new Item();
        item.setItemType(itemType);
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        return item;
    }

    //==비즈니스 로직==//
    /**
     * 재고 감소
     */
    public void removeStock(int count) {
        int restStock = quantity - count;
        if (restStock < 0) {
            throw new NotEnoughStockException("Need More Stock");
        }
        quantity = restStock;
    }

    /**
     * 재고 증가
     */
    public void addStock(int count) {
        removeStock(-count);
    }
}