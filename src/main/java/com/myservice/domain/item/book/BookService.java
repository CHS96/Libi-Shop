package com.myservice.domain.item.book;

import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemRepository;
import com.myservice.web.manager.items.book.BookSaveForm;
import com.myservice.web.manager.items.book.BookUpdateForm;
import com.myservice.web.paging.Paging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final ItemRepository itemRepository;

    public Long save(BookSaveForm form) {
        Book book = Book.createBook(form.getItemName(), form.getPrice(), form.getQuantity(), form.getAuthor());
        itemRepository.save(book);
        return book.getId();
    }

    public Long update(Long itemId, BookUpdateForm form) {
        Book book = (Book) itemRepository.findById(itemId).get();
        book.updateBook(form);
        return itemId;
    }

    public void removeItem(Long itemId) {
        itemRepository.deleteItem(itemId);
    }

    @Transactional(readOnly = true)
    public Item findItem(Long itemId) {
        Optional<Item> findItem = itemRepository.findById(itemId);
        return findItem.isEmpty() ? null : findItem.get();
    }

    @Transactional(readOnly = true)
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Item> findItemsByPaging(int startIndex) {
        return itemRepository.findItemByPaging(startIndex);
    }

    @Transactional(readOnly = true)
    public Long findItemTotalSize() {
        return itemRepository.findItemTotalSize();
    }
}