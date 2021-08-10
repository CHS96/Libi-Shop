package com.myservice.domain.cart;

import com.myservice.domain.cartline.CartLine;
import com.myservice.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    private List<CartLine> cartLines = new ArrayList<>();

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.setCart(this);
    }

    //==비즈니스 로직==//
    /**
     * 장바구니 내 전체 주문 가격 조회
     */
    public int getTotalPrice() {
        return cartLines.stream().mapToInt(CartLine::getPrice).sum();
    }
}
