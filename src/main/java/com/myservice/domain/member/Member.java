package com.myservice.domain.member;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class Member {

    @Id
    @GeneratedValue
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
