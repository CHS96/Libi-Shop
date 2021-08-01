package com.myservice.web.manager.items.movie;

import com.myservice.domain.item.ItemType;
import com.myservice.web.manager.items.ItemSaveForm;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MovieSaveForm extends ItemSaveForm {

    {
        setItemType(ItemType.MOVIE);
    }

    @NotNull
    private String genre;

    public static MovieSaveForm createMovieSaveForm(String itemName, Integer price, Integer quantity, String genre) {
        MovieSaveForm form = new MovieSaveForm();
        form.setItemName(itemName);
        form.setPrice(price);
        form.setQuantity(quantity);
        form.setGenre(genre);
        return form;
    }
}
