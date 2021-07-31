package com.myservice.domain.item;

import com.myservice.domain.member.Member;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ItemBasket {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemBasket")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
