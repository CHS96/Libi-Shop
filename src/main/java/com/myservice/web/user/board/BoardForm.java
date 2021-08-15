package com.myservice.web.user.board;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class BoardForm {

    @NotEmpty
    @Size(min = 0, max = 20)
    private String title;

    @NotEmpty
    @Size(min = 0, max = 200)
    private String content;
}
