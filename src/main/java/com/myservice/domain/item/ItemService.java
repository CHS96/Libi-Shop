package com.myservice.domain.item;

import com.myservice.domain.member.Member;
import com.myservice.web.manager.items.ItemSaveForm;
import com.myservice.web.manager.items.ItemUpdateForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

    Long save(ItemSaveForm form);

    Long update(Long itemId, ItemUpdateForm form);

    void removeItem(Long itemId);

    Item findItem(Long itemId);

    List<Item> findItems();

    void addCart(Member user, Long itemId, int count);
}
