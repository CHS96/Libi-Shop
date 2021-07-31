package com.myservice.domain.member;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("M")
public class Manager extends Member {

    public static Manager createManager(String username, String loginId, String password) {
        Manager manager = new Manager();
        manager.setUsername(username);
        manager.setLoginId(loginId);
        manager.setPassword(password);
        manager.setGrade(Grade.MANAGER);
        return manager;
    }
}