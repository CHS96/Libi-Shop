package com.myservice.domain.item;

import com.myservice.web.manager.items.book.BookUpdateForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;

    private Book() {
    }

    public static Book createEmptyBook() {
        Book book = new Book();
        book.setItemType(ItemType.BOOK);
        return book;
    }

    public static Book createBook(String itemName, Integer price, Integer quantity, String author) {
        Book book = new Book();
        book.setItemName(itemName);
        book.setPrice(price);
        book.setQuantity(quantity);
        book.setAuthor(author);
        book.setItemType(ItemType.BOOK);
        return book;
    }

    public static Book updateBook(Book book, BookUpdateForm form) {
        book.setItemName(form.getItemName());
        book.setPrice(form.getPrice());
        book.setQuantity(form.getQuantity());
        book.setAuthor(form.getAuthor());
        return book;
    }
}
