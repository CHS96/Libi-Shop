package com.myservice.web.user.items;

import com.myservice.domain.cart.Cart;
import com.myservice.domain.cart.CartService;
import com.myservice.domain.cartline.CartLine;
import com.myservice.domain.cartline.CartLineService;
import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemType;
import com.myservice.domain.item.book.BookService;
import com.myservice.domain.member.Member;
import com.myservice.web.paging.Paging;
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
    private final CartService cartService;

    private final String VIEW_PATH = "user/items/";

    @GetMapping("/page/{pageIndex}")
    public String items(@PathVariable int pageIndex, Model model) {
        List<Item> items = bookService.findItemsByPaging((pageIndex - 1) * Paging.MAX_SIZE);
        Long totalSize = bookService.findItemTotalSize();

        model.addAttribute("items", items);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("maxSize", Paging.MAX_SIZE);
        model.addAttribute("totalSize", totalSize);

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
        Cart cart = cartService.findCart(user);
        cartLineService.addCart(user, itemId, cart, count);

        List<CartLine> cartLines = cartLineService.findAllCartLine(user);
        List<CartForm> items = createCartForms(cartLines);
        int totalPrice = cart.getTotalPrice();

        model.addAttribute("items", items);
        model.addAttribute("totalPrice", totalPrice);
        return "user/cart/cart";
    }
}
