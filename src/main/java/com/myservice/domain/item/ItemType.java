package com.myservice.domain.item;

public enum ItemType {

    BOOK("도서"), FOOD("음식"), MOVIE("영화");

    private final String description;

    ItemType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
