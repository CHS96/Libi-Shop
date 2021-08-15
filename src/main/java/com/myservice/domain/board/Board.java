package com.myservice.domain.board;

import com.myservice.domain.member.Member;
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

    private String title;
    private String content;
    private LocalDate dateTime;
    private Long views;

    private Board() {
    }

    public static Board createBoard(String title, String message, Member user) {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(message);
        board.setMember(user);
        board.setDateTime(LocalDate.now());
        board.setViews(0L);
        return board;
    }
}