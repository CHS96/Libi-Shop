package com.myservice.web.manager.items;

import com.myservice.domain.item.ItemType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookSaveForm extends ItemSaveForm {

    {
        setItemType(ItemType.BOOK);
    }

    @NotNull
    private String author;

    public static BookSaveForm createBookSaveForm(String itemName, Integer price, Integer quantity, String author) {
        BookSaveForm form = new BookSaveForm();
        form.setItemName(itemName);
        form.setPrice(price);
        form.setQuantity(quantity);
        form.setAuthor(author);
        return form;
    }
}
