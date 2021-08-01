package com.myservice.domain.item;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("M")
public class Movie extends Item {

    private String genre;

    private Movie() {};

    public static Movie createEmptyMovie() {
        Movie movie = new Movie();
        movie.setItemType(ItemType.MOVIE);
        return movie;
    }

    public static Movie createMovie(String itemName, Integer price, Integer quantity, String genre) {
        Movie movie = new Movie();
        movie.setItemName(itemName);
        movie.setPrice(price);
        movie.setQuantity(quantity);
        movie.setGenre(genre);
        movie.setItemType(ItemType.MOVIE);
        return movie;
    }
}
