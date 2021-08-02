package com.myservice.domain.member;

import com.myservice.domain.itemBasket.ItemBasket;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
@DiscriminatorValue("U")
public class User extends Member {

    {
        setGrade(Grade.USER);
    }

    @OneToOne(mappedBy = "member")
    private ItemBasket itemBasket;

    public static User createUser(String username, String loginId, String password) {
        User user = new User();
        user.setUsername(username);
        user.setLoginId(loginId);
        user.setPassword(password);
        return user;
    }
}
