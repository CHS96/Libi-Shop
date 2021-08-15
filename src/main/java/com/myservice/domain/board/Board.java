package com.myservice.domain.board;

import com.myservice.domain.member.Member;
import com.myservice.web.user.board.BoardUpdateForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String writer;
    private String title;
    private String content;
    private LocalDate dateTime;
    private Long views;

    private Board() {
    }

    public static Board createBoard(String title, String content, Member user) {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setMember(user);
        board.setDateTime(LocalDate.now());
        board.setViews(0L);
        board.setWriter(user.getLoginId());
        return board;
    }

    public void addViews() {
        this.views++;
    }

    public Board updateBoard(BoardUpdateForm form) {
        this.setTitle(form.getTitle());
        this.setContent(form.getContent());
        return this;
    }
}