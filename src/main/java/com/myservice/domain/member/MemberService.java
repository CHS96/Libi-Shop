package com.myservice.domain.member;

import com.myservice.domain.cart.Cart;
import com.myservice.domain.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final CartService cartService;
    private final PasswordEncoder passwordEncoder;

    public void save(Member member) {
        member.setPassword(encode(member.getPassword()));
        Cart cart = new Cart();
        cartService.save(cart);
        cart.setMember(member);
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    private String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
