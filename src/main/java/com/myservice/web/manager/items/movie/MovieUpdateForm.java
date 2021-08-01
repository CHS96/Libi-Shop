package com.myservice.web.manager.items.movie;

import com.myservice.domain.item.ItemType;
import com.myservice.web.manager.items.ItemUpdateForm;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MovieUpdateForm extends ItemUpdateForm {

    {
        setItemType(ItemType.BOOK);
    }

    @NotNull
    private String genre;

    public static MovieUpdateForm createMovieUpdateForm(String itemName, Integer price, Integer quantity, String genre) {
        MovieUpdateForm form = new MovieUpdateForm();
        form.setItemName(itemName);
        form.setPrice(price);
        form.setQuantity(quantity);
        form.setGenre(genre);
        return form;
    }
}
