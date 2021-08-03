package com.myservice.domain.item.movie;

import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemType;
import com.myservice.web.manager.items.movie.MovieUpdateForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("M")
public class Movie extends Item {

    private String genre;

    private Movie() {}

    {
        setItemType(ItemType.MOVIE);
    }

    //==생성 메서드==//
    public static Movie createEmptyMovie() {
        Movie movie = new Movie();
        return movie;
    }

    public static Movie createMovie(String itemName, Integer price, Integer quantity, String genre) {
        Movie movie = new Movie();
        movie.setItemName(itemName);
        movie.setPrice(price);
        movie.setQuantity(quantity);
        movie.setGenre(genre);
        return movie;
    }

    //==비즈니스 로직==//
    public Movie updateMovie(MovieUpdateForm form) {
        this.setItemName(form.getItemName());
        this.setPrice(form.getPrice());
        this.setQuantity(form.getQuantity());
        this.setGenre(form.getGenre());
        return this;
    }
}
