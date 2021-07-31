package com.myservice.domain.item;

public class CreateItemTypeValues {

    private static final ItemType[] values = ItemType.values();

    private CreateItemTypeValues() {}

    public static ItemType[] getInstance() {
        return values;
    }
}
