package com.myservice.domain.item;

import com.myservice.domain.itemBasket.ItemBasket;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository {

    Long save(Item item);

    Optional<Item> findById(Long id);

    List<Item> findAll();

    void delete(Long itemId);

    void saveItem(ItemBasket itemBasket);
}
