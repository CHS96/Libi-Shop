package com.myservice.domain.itemBasket;

import com.myservice.domain.item.Item;
import com.myservice.domain.member.Member;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class ItemBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemBasket_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "itemBasket")
    private List<Item> items = new ArrayList<>();

    /**
     * 장바구니 Item 추가
     */
    public void addItem(Item item) {
        items.add(item);
    }
}
