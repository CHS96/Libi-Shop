package com.myservice.web.user.items;

import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemType;
import com.myservice.domain.item.book.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller(value = "UserItemController")
@RequestMapping("/user/items")
@RequiredArgsConstructor
public class ItemController {

    private final BookService bookService;

    private final String VIEW_PATH = "user/items/";

    @GetMapping()
    public String items(Model model) {
        List<Item> items = bookService.findItems();
        model.addAttribute("items", items);
        return VIEW_PATH + "items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = bookService.findItem(itemId);
        ItemType itemType = item.getItemType();
        model.addAttribute("item", item);

        if (itemType == ItemType.BOOK) return VIEW_PATH + "book/item";
        else if (itemType == ItemType.FOOD) return VIEW_PATH + "food/item";
        else return VIEW_PATH + "movie/item";
    }
}
