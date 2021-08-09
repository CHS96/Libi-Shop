package com.myservice.web.user.cart;

import com.myservice.domain.cartline.CartLine;
import com.myservice.domain.cartline.CartLineService;
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
import java.util.List;

import static com.myservice.web.user.cart.CartForm.createCartForms;

@Slf4j
@Controller
@RequestMapping("/user/cart")
@RequiredArgsConstructor
public class CartController {

    private final BookService bookService;
    private final CartLineService cartLineService;

    private final String VIEW_PATH = "user/items/";

    @GetMapping
    public String cart(HttpSession session, Model model) {
        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<CartLine> allCartLine = cartLineService.findAllCartLine(user);
        user.getCart().setCartLines(allCartLine);
        List<CartForm> items = createCartForms(allCartLine);
        int totalPrice = user.getCart().getTotalPrice();

        model.addAttribute("items", items);
        model.addAttribute("totalPrice", totalPrice);
        return VIEW_PATH + "cart";
    }

    @GetMapping("/edit/{itemId}")
    public String editCart(@PathVariable Long itemId, HttpSession session, Model model) {
        Item item = bookService.findItem(itemId);
        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        CartLine cartLine = cartLineService.findCartLineWithCartAndItem(user.getCart(), item);
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
            return "redirect:/user/cart/{itemId}";
        }

        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        cartLineService.editCart(user, itemId, count);

        List<CartLine> cartLines = cartLineService.findAllCartLine(user);
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
        cartLineService.deleteCartLine(user, item);
        return "redirect:/user/cart";
    }
}
