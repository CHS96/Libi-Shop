package com.myservice.domain.item;

import com.myservice.web.manager.items.ItemSaveForm;
import com.myservice.web.manager.items.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public Long save(ItemSaveForm form) {
        Item item = new Item(form.getItemName(), form.getPrice(), form.getQuantity());
        itemRepository.save(item);
        return item.getId();
    }

    public Long update(Long itemId, ItemUpdateForm form) {
        Item item = itemRepository.findById(itemId).get();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());
        return itemId;
    }

    @Transactional(readOnly = true)
    public Item findItem(Long itemId) {
        return itemRepository.findById(itemId).get();
    }

    @Transactional(readOnly = true)
    public List<Item> findItems() {
        return itemRepository.findAll();
    }
}
