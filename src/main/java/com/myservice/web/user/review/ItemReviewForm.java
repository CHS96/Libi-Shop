package com.myservice.web.user.review;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ItemReviewForm {

    @NotNull
    private Long itemId;

    @NotEmpty
    @Size(min = 0, max = 20)
    private String title;

    @NotEmpty
    @Size(min = 0, max = 200)
    private String message;

    @NotNull
    @Min(0) @Max(5)
    private Double star;
}
