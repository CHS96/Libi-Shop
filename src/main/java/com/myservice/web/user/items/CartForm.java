package com.myservice.web.user.items;

import com.myservice.domain.cart.CartLine;
import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class CartForm {

    private Long id;
    private String itemName;
    private ItemType itemType;
    private int price;
    private int quantity;
    private int totalPrice;

    private CartForm() {}

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

    public static List<CartForm> createCartForms(List<CartLine> cartLines) {
        List<CartForm> items = new ArrayList<>();
        for (CartLine cartLine : cartLines) {
            Item item = cartLine.getItem();
            items.add(CartForm.createCartForm(item.getId(), item.getItemName(), item.getItemType(), item.getPrice(), cartLine.getCount()));
        }
        return items;
    }
}
