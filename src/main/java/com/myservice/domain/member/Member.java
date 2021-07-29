package com.myservice.domain.member;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    private Grade grade = Grade.USER;
}
