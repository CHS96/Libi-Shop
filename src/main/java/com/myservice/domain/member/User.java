package com.myservice.domain.member;

import com.myservice.domain.cart.Cart;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Data
@Entity
@DiscriminatorValue("U")
public class User extends Member {

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Cart cart;

    public static User createUser(String username, String loginId, String password) {
        User user = new User();
        user.setUsername(username);
        user.setLoginId(loginId);
        user.setPassword(password);
        user.setGrade(Grade.USER);
        return user;
    }
}
