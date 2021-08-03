package com.myservice.domain.member;

import com.myservice.domain.cart.Cart;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@DiscriminatorColumn(name = "dtype")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    //==생성 메서드==//
    public static Member createManager(String username, String loginId, String password) {
        Member manager = new Member();
        manager.setGrade(Grade.MANAGER);
        manager.setUsername(username);
        manager.setLoginId(loginId);
        manager.setPassword(password);
        return manager;
    }

    public static Member createUser(String username, String loginId, String password) {
        Member user = new Member();
        user.setGrade(Grade.USER);
        user.setUsername(username);
        user.setLoginId(loginId);
        user.setPassword(password);
        new Cart().setMember(user);
        return user;
    }

    //==비즈니스 로직==//
}