package com.myservice.domain.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PaymentRepository {

    private final EntityManager em;

    public Long save(Payment payment) {
        em.persist(payment);
        return payment.getId();
    }

}
