package com.myservice.domain.member;

import com.myservice.domain.item.ItemBasket;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    private Grade grade = Grade.USER;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    List<ItemBasket> itemBaskets = new ArrayList<>();

    public static Member createMember(String username, String loginId, String password) {
        Member member = new Member();
        member.setUsername(username);
        member.setLoginId(loginId);
        member.setPassword(password);
        return member;
    }
}