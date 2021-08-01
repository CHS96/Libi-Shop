package com.myservice.domain.member;

import com.myservice.domain.itemBasket.ItemBasket;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@DiscriminatorValue("U")
public class User extends Member {

    @OneToMany(mappedBy = "member")
    private List<ItemBasket> itemBaskets = new ArrayList<>();

    public static User createUser(String username, String loginId, String password) {
        User user = new User();
        user.setUsername(username);
        user.setLoginId(loginId);
        user.setPassword(password);
        user.setGrade(Grade.USER);
        return user;
    }
}
