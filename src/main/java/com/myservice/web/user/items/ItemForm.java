package com.myservice.web.user.items;

import com.myservice.domain.item.ItemType;
import lombok.Data;

@Data
public class ItemForm {

    private Long id;
    private String itemName;
    private ItemType itemType;
    private int totalPrice;
    private int quantity;

    private ItemForm() {}

    public static ItemForm createItemForm(Long id, String itemName, ItemType itemType, int totalPrice, int quantity) {
        ItemForm form = new ItemForm();
        form.setId(id);
        form.setItemName(itemName);
        form.setItemType(itemType);
        form.setTotalPrice(totalPrice);
        form.setQuantity(quantity);
        return form;
    }
}
