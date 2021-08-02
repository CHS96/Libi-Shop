package com.myservice.web.user.items;

import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemService;
import com.myservice.domain.item.ItemType;
import com.myservice.domain.itemBasket.ItemBasket;
import com.myservice.domain.member.MemberService;
import com.myservice.domain.member.User;
import com.myservice.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller(value = "UserItemController")
@RequestMapping("/user/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final MemberService memberService;
    private final EntityManager em;

    private final String VIEW_PATH = "user/items/";

    @GetMapping()
    public String items(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return VIEW_PATH + "items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemService.findItem(itemId);
        ItemType itemType = item.getItemType();
        model.addAttribute("item", item);

        if (itemType == ItemType.BOOK) {
            return VIEW_PATH + "book/item";
        } else if (itemType == ItemType.FOOD) {
            return VIEW_PATH + "food/item";
        } else if (itemType == ItemType.MOVIE) {
            return VIEW_PATH + "movie/item";
        }
        return "redirect:/";
    }

    @Transactional
    @PostMapping("/{itemId}")
    public String addItem(@PathVariable Long itemId, @RequestParam("count") int count, HttpSession session) {
        User user = (User) session.getAttribute(SessionConst.LOGIN_MEMBER);

        return "redirect:/user/items";
    }
}
