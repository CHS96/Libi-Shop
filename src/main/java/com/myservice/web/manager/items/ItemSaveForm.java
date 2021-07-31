package com.myservice.web.manager.items;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Slf4j
@Data
public class ItemSaveForm {

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NotNull
    @Max(value = 9999)
    private Integer quantity;

    public static ItemSaveForm createItemSaveForm(String itemName, Integer price, Integer quantity) {
        ItemSaveForm form = new ItemSaveForm();
        form.setItemName(itemName);
        form.setPrice(price);
        form.setQuantity(quantity);
        return form;
    }
}