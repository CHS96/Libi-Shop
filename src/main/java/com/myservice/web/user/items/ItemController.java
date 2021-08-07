package com.myservice.web.user.items;

import com.myservice.domain.cart.CartLine;
import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemType;
import com.myservice.domain.item.book.Book;
import com.myservice.domain.item.book.BookService;
import com.myservice.domain.item.food.Food;
import com.myservice.domain.item.movie.Movie;
import com.myservice.domain.member.Member;
import com.myservice.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<CartForm> items = createCartForms(bookService.findAllCartLine(user));
        int totalPrice = items.stream().mapToInt(CartForm::getPrice).sum();

        model.addAttribute("items", items);
        model.addAttribute("totalPrice", totalPrice);
        return VIEW_PATH + "cart";
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
        bookService.addCart(user, itemId, count);

        List<CartLine> cartLines = bookService.findAllCartLine(user);
        user.getCart().setCartLines(cartLines);
        List<CartForm> items = createCartForms(cartLines);
        int totalPrice = user.getCart().getTotalPrice();

        model.addAttribute("items", items);
        model.addAttribute("totalPrice", totalPrice);
        return VIEW_PATH + "cart";
    }

    @GetMapping("/edit/{itemId}")
    public String editCart(@PathVariable Long itemId, HttpSession session, Model model) {
        Item item = bookService.findItem(itemId);
        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        CartLine cartLine = bookService.findCartLineWithCartAndItem(user.getCart(), item);
        ItemType itemType = item.getItemType();

        if (itemType == ItemType.BOOK) {
            Book book = Book.createBook(item.getItemName(), item.getPrice(), cartLine.getCount(), ((Book) item).getAuthor());
            book.setId(itemId);
            model.addAttribute("item", book);
            return VIEW_PATH + "book/editItem";
        } else if (itemType == ItemType.FOOD) {
            Food food = Food.createFood(item.getItemName(), item.getPrice(), cartLine.getCount(), ((Food) item).getFoodType());
            food.setId(itemId);
            model.addAttribute("item", food);
            return VIEW_PATH + "food/editItem";
        } else {
            Movie movie = Movie.createMovie(item.getItemName(), item.getPrice(), cartLine.getCount(), ((Movie) item).getGenre());
            movie.setId(itemId);
            model.addAttribute("item", movie);
            return VIEW_PATH + "movie/editItem";
        }
    }

    @PostMapping("/edit/{itemId}")
    public String editCart(@PathVariable Long itemId, @RequestParam("count") int count, HttpSession session, Model model) {
        int itemStock = bookService.findItem(itemId).getQuantity();

        if (count < 0 || count > itemStock) {
            return "redirect:/user/items/{itemId}";
        }

        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        bookService.editCart(user, itemId, count);

        List<CartLine> cartLines = bookService.findAllCartLine(user);
        user.getCart().setCartLines(cartLines);
        List<CartForm> items = createCartForms(cartLines);
        int totalPrice = user.getCart().getTotalPrice();

        model.addAttribute("items", items);
        model.addAttribute("totalPrice", totalPrice);
        return VIEW_PATH + "cart";
    }

    @GetMapping("/delete/{itemId}")
    public String deleteCartLine(@PathVariable Long itemId, HttpSession session, Model model) {
        Item item = bookService.findItem(itemId);
        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        bookService.deleteCartLine(user, item);
        return "redirect:/user/items/cart";
    }

    @GetMapping("/payment")
    public String payment(HttpSession session, Model model) {
        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        bookService.payment(user);
        return "redirect:/user/items/cart";
    }

    private List<CartForm> createCartForms(List<CartLine> cartLines) {
        List<CartForm> items = new ArrayList<>();
        for (CartLine cartLine : cartLines) {
            Item item = cartLine.getItem();
            items.add(CartForm.createCartForm(item.getId(), item.getItemName(), item.getItemType(), item.getPrice(), cartLine.getCount()));
        }
        return items;
    }
}
