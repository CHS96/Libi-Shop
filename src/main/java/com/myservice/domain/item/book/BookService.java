package com.myservice.domain.item.book;

import com.myservice.domain.cart.Cart;
import com.myservice.domain.cartline.CartLine;
import com.myservice.domain.cartline.CartLineRepository;
import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemRepository;
import com.myservice.domain.member.Member;
import com.myservice.domain.payment.Payment;
import com.myservice.web.manager.items.book.BookSaveForm;
import com.myservice.web.manager.items.book.BookUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
        return itemRepository.findById(itemId).get();
    }

    @Transactional(readOnly = true)
    public List<Item> findItems() {
        return itemRepository.findAll();
    }
}