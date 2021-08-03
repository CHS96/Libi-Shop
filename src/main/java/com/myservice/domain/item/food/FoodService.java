package com.myservice.domain.item.food;

import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemRepository;
import com.myservice.domain.member.Member;
import com.myservice.web.manager.items.food.FoodSaveForm;
import com.myservice.web.manager.items.food.FoodUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public void removeItem(Long itemId) {
        itemRepository.delete(itemId);
    }

    @Transactional(readOnly = true)
    public Item findItem(Long itemId) {
        return itemRepository.findById(itemId).get();
    }

    @Transactional(readOnly = true)
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    /**
     * 장바구니 Item 추가
     */
    public void addCart(Member user, Long itemId, int count) {
        //Item 재고 감소
        Item item = itemRepository.findById(itemId).get();
        item.removeStock(count);
    }
}