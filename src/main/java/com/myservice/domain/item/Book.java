package com.myservice.domain.item;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Data
@Entity
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;

    private Book() {};

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
}