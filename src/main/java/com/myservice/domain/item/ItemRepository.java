package com.myservice.domain.item;

import com.myservice.web.paging.Paging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public Long save(Item item) {
        em.persist(item);
        return item.getId();
    }

    public Optional<Item> findById(Long id) {
        Item item = em.find(Item.class, id);
        return Optional.ofNullable(item);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i order by i.id asc", Item.class)
                .getResultList();
    }

    public void deleteItem(Long itemId) {
        Item item = findById(itemId).get();
        em.remove(item);
    }

    public List<Item> findItemByPaging(int startIndex) {
        return em.createQuery("select i from Item i order by i.id asc", Item.class)
                .setFirstResult(startIndex)
                .setMaxResults(Paging.MAX_SIZE)
                .getResultList();
    }

    /**
     * 현재 Item 총 개수 count query
     */
    public Long findItemTotalSize() {
        return (Long) em.createQuery("select count(*) from Item i").getSingleResult();
    }
}
