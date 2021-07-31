package com.myservice.web.user.items;

import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemRepository;
import com.myservice.web.manager.items.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/items/user")
@RequiredArgsConstructor
public class UserItemController {

    private final ItemRepository itemRepository;

    @GetMapping()
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "items/user/items";
    }

    @GetMapping("{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId).get();
        model.addAttribute("item", item);
        return "items/user/item";
    }

    @GetMapping("buy/{itemId}")
    public String buyItem(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId).get();
        if (item.getQuantity() > 0) {
            item.setQuantity(item.getQuantity() - 1);
        }

        return "redirect:/items/user";
    }
}
