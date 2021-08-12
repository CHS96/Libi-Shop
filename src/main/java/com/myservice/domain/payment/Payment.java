package com.myservice.domain.payment;

import com.myservice.domain.cart.Cart;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private Integer price;

    private LocalDateTime dateTime;

    private Payment(){}

    public static Payment createPayment(Cart cart, int price) {
        Payment payment = new Payment();
        payment.setCart(cart);
        payment.setPrice(price);
        payment.setDateTime(LocalDateTime.now());
        return payment;
    }
}
