package com.myservice.web.user.items;

import com.myservice.domain.item.ItemType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartForm {

    private Long id;
    private String itemName;
    private ItemType itemType;
    private int price;
    private int quantity;
    private int totalPrice;

    private CartForm() {
    }

    public static CartForm createCartForm(Long id, String itemName, ItemType itemType, int price, int quantity) {
        CartForm cartForm = new CartForm();
        cartForm.setId(id);
        cartForm.setItemName(itemName);
        cartForm.setItemType(itemType);
        cartForm.setPrice(price);
        cartForm.setQuantity(quantity);
        cartForm.setTotalPrice(price * quantity);
        return cartForm;
    }
}
