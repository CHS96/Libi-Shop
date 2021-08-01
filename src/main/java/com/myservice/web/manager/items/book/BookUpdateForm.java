package com.myservice.web.manager.items.book;

import com.myservice.domain.item.ItemType;
import com.myservice.web.manager.items.ItemUpdateForm;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookUpdateForm extends ItemUpdateForm {

    {
        setItemType(ItemType.BOOK);
    }

    @NotNull
    private String author;

    public static BookUpdateForm createBookUpdateForm(String itemName, Integer price, Integer quantity, String author) {
        BookUpdateForm form = new BookUpdateForm();
        form.setItemName(itemName);
        form.setPrice(price);
        form.setQuantity(quantity);
        form.setAuthor(author);
        return form;
    }
}
