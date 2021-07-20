package com.myservice.domain;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @ToString
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String name;

    private String email;

    private String password;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
