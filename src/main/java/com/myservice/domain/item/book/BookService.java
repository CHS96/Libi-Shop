package com.myservice.domain.item.book;

import com.myservice.domain.cart.Cart;
import com.myservice.domain.cart.CartLine;
import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemRepository;
import com.myservice.domain.member.Member;
import com.myservice.web.manager.items.book.BookSaveForm;
import com.myservice.web.manager.items.book.BookUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * add Item in Cart of User
     */
    public void addCart(Member user, Long itemId, int count) {
        //Item 재고 감소
        Item item = itemRepository.findById(itemId).get();
        item.removeStock(count);

        //User에게 동일한 상품이 존재하면 기존의 상품 수정
        CartLine cartLine = isExistCartLine(user, item, count);
        if (cartLine != null) {
            cartLine.addCount(count);
            return;
        }

        //그렇지 않다면 새로운 CartLine 생성
        cartLine = CartLine.createCareLine(item, count);
        cartLine.setCart(user.getCart());
        itemRepository.saveCartLine(cartLine);
    }

    /**
     * edit Item in Cart of User
     */
    public void editCart(Member user, Long itemId, int count) {

    }

    /**
     * User에게 동일한 상품 존재 유무 판단
     */
    private CartLine isExistCartLine(Member user, Item item, int count) {
        return user.getCart().getCartLines().stream()
                .filter(cl -> cl.getId() == item.getId())
                .findFirst()
                .orElse(null);
    }
}