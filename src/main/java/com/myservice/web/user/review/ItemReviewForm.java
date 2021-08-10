package com.myservice.web.user.review;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ItemReviewForm {

    @NotNull
    private Long itemId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String message;

    @NotNull
    private Double star;
}