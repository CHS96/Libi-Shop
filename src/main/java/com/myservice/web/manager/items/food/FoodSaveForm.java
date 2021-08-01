package com.myservice.web.manager.items.food;

import com.myservice.domain.item.ItemType;
import com.myservice.web.manager.items.ItemSaveForm;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FoodSaveForm extends ItemSaveForm {

    {
        setItemType(ItemType.FOOD);
    }

    @NotNull
    private String foodType;

    public static FoodSaveForm createFoodSaveForm(String itemName, Integer price, Integer quantity, String foodType) {
        FoodSaveForm form = new FoodSaveForm();
        form.setItemName(itemName);
        form.setPrice(price);
        form.setQuantity(quantity);
        form.setFoodType(foodType);
        return form;
    }
}
