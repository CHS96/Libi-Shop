package com.myservice.domain.item.food;

import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemRepository;
import com.myservice.web.manager.items.food.FoodSaveForm;
import com.myservice.web.manager.items.food.FoodUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FoodService {

    private final ItemRepository itemRepository;

    public Long save(FoodSaveForm form) {
        Food food = Food.createFood(form.getItemName(), form.getPrice(), form.getQuantity(), form.getFoodType());
        itemRepository.save(food);
        return food.getId();
    }

    public Long update(Long itemId, FoodUpdateForm form) {
        Food food = (Food) itemRepository.findById(itemId).get();
        food.updateFood(form);
        return itemId;
    }

    @Transactional(readOnly = true)
    public Item findItem(Long itemId) {
        return itemRepository.findById(itemId).get();
    }
}