package com.myservice.domain.item;

import com.myservice.domain.cart.Cart;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryItemRepository implements ItemRepository {

    private static final Map<Long, Item> store = new ConcurrentHashMap<>(); //static
    private static long sequence = 0L; //static

    public Long save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item.getId();
    }

    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(Long itemId) {
        store.remove(itemId);
    }

    @Override
    public void saveItem(Cart cart) {

    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId).get();
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
