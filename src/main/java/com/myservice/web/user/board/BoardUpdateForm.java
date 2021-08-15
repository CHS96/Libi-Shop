package com.myservice.web.user.board;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class BoardUpdateForm {

    @NotEmpty
    @Size(min = 0, max = 20)
    private String title;

    private String writer;

    @NotEmpty
    @Size(min = 10, max = 200)
    private String content;

    private LocalDate dateTime;

    private Long views;
}
