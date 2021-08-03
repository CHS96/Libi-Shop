package com.myservice.domain.item.book;

import com.myservice.domain.cart.Cart;
import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemType;
import com.myservice.domain.member.User;
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

    private Book() { }

    {
        setItemType(ItemType.BOOK);
    }

    //==연관관계 메서드==//

    //==생성 메서드==//
    public static Book createEmptyBook() {
        Book book = new Book();
        return book;
    }

    public static Book createBook(String itemName, Integer price, Integer quantity, String author) {
        Book book = new Book();
        book.setItemName(itemName);
        book.setPrice(price);
        book.setQuantity(quantity);
        book.setAuthor(author);
        return book;
    }

    //==비즈니스 로직==//
    public Book updateBook(BookUpdateForm form) {
        this.setItemName(form.getItemName());
        this.setItemName(form.getItemName());
        this.setPrice(form.getPrice());
        this.setQuantity(form.getQuantity());
        this.setAuthor(form.getAuthor());
        return this;
    }
}
