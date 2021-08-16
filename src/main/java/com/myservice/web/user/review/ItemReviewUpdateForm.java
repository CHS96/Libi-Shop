package com.myservice.web.user.review;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class ItemReviewUpdateForm {

    @NotEmpty
    @Size(min = 0, max = 20)
    private String title;

    @NotEmpty
    @Size(min = 10, max = 200)
    private String content;

    @NotNull
    @Min(0) @Max(5)
    private Double star;

    private LocalDate dateTime;

    private Long views;
}