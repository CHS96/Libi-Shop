package com.myservice.domain.item;

import com.myservice.web.manager.items.BookSaveForm;
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
        if (form instanceof BookSaveForm) {
            Book book = Book.createBook(form.getItemName(), form.getPrice(), form.getQuantity(), ((BookSaveForm) form).getAuthor());
            itemRepository.save(book);
            return book.getId();
        } else {
            return null;
        }
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