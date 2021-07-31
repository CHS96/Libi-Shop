package com.myservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository {

    Long save(Item item);

    Optional<Item> findById(Long id);

    List<Item> findAll();
}
