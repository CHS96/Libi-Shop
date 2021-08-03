package com.myservice.domain.item.food;

import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemType;
import com.myservice.web.manager.items.food.FoodUpdateForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("F")
public class Food extends Item {

    private String foodType;

    private Food() {}

    {
        setItemType(ItemType.FOOD);
    }

    //==생성 메서드==//
    public static Food createEmptyFood() {
        Food food = new Food();
        return food;
    }

    public static Food createFood(String itemName, Integer price, Integer quantity, String foodType) {
        Food food = new Food();
        food.setItemName(itemName);
        food.setPrice(price);
        food.setQuantity(quantity);
        food.setFoodType(foodType);
        return food;
    }

    //==비즈니스 로직==//
    public Food updateFood(FoodUpdateForm form) {
        this.setItemName(form.getItemName());
        this.setPrice(form.getPrice());
        this.setQuantity(form.getQuantity());
        this.setFoodType(form.getFoodType());
        return this;
    }
}
