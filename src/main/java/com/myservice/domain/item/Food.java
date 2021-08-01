package com.myservice.domain.item;

import com.myservice.web.manager.items.food.FoodUpdateForm;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("F")
public class Food extends Item {

    private String foodType;

    private Food() {}

    public static Food createEmptyFood() {
        Food food = new Food();
        food.setItemType(ItemType.FOOD);
        return food;
    }

    public static Food createFood(String itemName, Integer price, Integer quantity, String foodType) {
        Food food = new Food();
        food.setItemName(itemName);
        food.setPrice(price);
        food.setQuantity(quantity);
        food.setFoodType(foodType);
        food.setItemType(ItemType.FOOD);
        return food;
    }

    public static Food updateFood(Food food, FoodUpdateForm form) {
        food.setItemName(form.getItemName());
        food.setPrice(form.getPrice());
        food.setQuantity(form.getQuantity());
        food.setFoodType(form.getFoodType());
        return food;
    }
}
