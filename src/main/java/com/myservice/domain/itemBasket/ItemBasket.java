package com.myservice.domain.itemBasket;

import com.myservice.domain.item.Item;
import com.myservice.domain.member.Member;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ItemBasket {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemBasket_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "itemBasket")
    private List<Item> items = new ArrayList<>();
}
