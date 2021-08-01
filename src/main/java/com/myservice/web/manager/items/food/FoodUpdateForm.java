package com.myservice.web.manager.items.food;

import com.myservice.domain.item.ItemType;
import com.myservice.web.manager.items.ItemUpdateForm;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FoodUpdateForm extends ItemUpdateForm {

    {
        setItemType(ItemType.FOOD);
    }

    @NotNull
    private String foodType;

    public static FoodUpdateForm createFoodUpdateForm(String itemName, Integer price, Integer quantity, String foodType) {
        FoodUpdateForm form = new FoodUpdateForm();
        form.setItemName(itemName);
        form.setPrice(price);
        form.setQuantity(quantity);
        form.setFoodType(foodType);
        return form;
    }
}
