package com.myservice.web.user.items;

import com.myservice.domain.cartline.CartLine;
import com.myservice.domain.cartline.CartLineService;
import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemType;
import com.myservice.domain.item.book.BookService;
import com.myservice.domain.member.Member;
import com.myservice.web.session.SessionConst;
import com.myservice.web.user.cart.CartForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.myservice.web.user.cart.CartForm.createCartForms;

@Slf4j
@Controller(value = "UserItemController")
@RequestMapping("/user/items")
@RequiredArgsConstructor
public class ItemController {

    private final BookService bookService;
    private final CartLineService cartLineService;

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

    @PostMapping("/{itemId}")
    public String addCart(@PathVariable Long itemId, @RequestParam("count") int count, HttpSession session, Model model) {
        int itemStock = bookService.findItem(itemId).getQuantity();

        if (count < 0 || count > itemStock) {
            return "redirect:/user/items/{itemId}";
        }

        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        cartLineService.addCart(user, itemId, count);

        List<CartLine> cartLines = cartLineService.findAllCartLine(user);
        List<CartForm> items = createCartForms(cartLines);
        int totalPrice = user.getCart().getTotalPrice();

        model.addAttribute("items", items);
        model.addAttribute("totalPrice", totalPrice);
        return VIEW_PATH + "cart";
    }
}
