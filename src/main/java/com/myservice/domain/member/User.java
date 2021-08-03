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

    {
        setGrade(Grade.USER);
    }

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Cart cart;

    //==연관관계 메서드==//
    public void setCart(Cart cart) {
        this.cart = cart;
        cart.setMember(this);
    }

    //==생성 메서드==//
    public static User createUser(String username, String loginId, String password) {
        User user = new User();
        user.setUsername(username);
        user.setLoginId(loginId);
        user.setPassword(password);
        return user;
    }

    //==비즈니스 로직==//
}
