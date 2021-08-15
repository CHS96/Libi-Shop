package com.myservice.domain.cart;

import com.myservice.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;

    public Long save(Cart cart) { return cartRepository.save(cart); }
    public Cart findCart(Member user) {
        return cartRepository.findCart(user);
    }
}
