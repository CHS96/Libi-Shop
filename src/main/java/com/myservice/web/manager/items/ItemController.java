package com.myservice.web.manager.items;

import com.myservice.domain.item.*;
import com.myservice.web.manager.items.book.BookSaveForm;
import com.myservice.web.manager.items.book.BookUpdateForm;
import com.myservice.web.manager.items.food.FoodSaveForm;
import com.myservice.web.manager.items.food.FoodUpdateForm;
import com.myservice.web.manager.items.movie.MovieSaveForm;
import com.myservice.web.manager.items.movie.MovieUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/manager/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    private final String VIEW_PATH = "manager/items/";

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return CreateItemTypeValues.getInstance();
    }

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

    @GetMapping("/add")
    public String selectItemTypeForm(Model model) {
        //라디오 버튼 기본 값 : Book
        Book book = Book.createEmptyBook();
        model.addAttribute("item", book);
        return VIEW_PATH + "selectTypeForm";
    }

    @PostMapping("/add")
    public String selectItemTypeForm(@Validated @ModelAttribute ItemType itemType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return VIEW_PATH + "selectTypeForm";
        }

        if (itemType == ItemType.BOOK) {
            return "redirect:/manager/items/addBook";
        } else if (itemType == ItemType.FOOD) {
            return "redirect:/manager/items/addFood";
        } else if (itemType == ItemType.MOVIE) {
            return "redirect:/manager/items/addMovie";
        }
        return "redirect:/";
    }

    @GetMapping("/addBook")
    public String addBookForm(Model model) {
        Book book = Book.createEmptyBook();
        model.addAttribute("book", book);
        return VIEW_PATH + "book/addForm";
    }

    @PostMapping("/addBook")
    public String addBookForm(@Validated @ModelAttribute("book") BookSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //특정 필드 예외가 아닌 전체 예외
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return VIEW_PATH + "book/addForm";
        }

        Long savedId = itemService.save(form);
        redirectAttributes.addAttribute("itemId", savedId);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/manager/items/{itemId}";
    }

    @GetMapping("/addFood")
    public String addFoodForm(Model model) {
        Food food = Food.createEmptyFood();
        model.addAttribute("food", food);
        return VIEW_PATH + "food/addForm";
    }

    @PostMapping("/addFood")
    public String addFoodForm(@Validated @ModelAttribute("food") FoodSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //특정 필드 예외가 아닌 전체 예외
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return VIEW_PATH + "food/addForm";
        }

        Long savedId = itemService.save(form);
        redirectAttributes.addAttribute("itemId", savedId);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/manager/items/{itemId}";
    }

    @GetMapping("/addMovie")
    public String addMovieForm(Model model) {
        Movie movie = Movie.createEmptyMovie();
        model.addAttribute("movie", movie);
        return VIEW_PATH + "movie/addForm";
    }

    @PostMapping("/addMovie")
    public String addItemForm(@Validated @ModelAttribute("movie") MovieSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //특정 필드 예외가 아닌 전체 예외
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return VIEW_PATH + "movie/addForm";
        }

        Long savedId = itemService.save(form);
        redirectAttributes.addAttribute("itemId", savedId);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/manager/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit/book")
    public String editBookForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findItem(itemId);
        model.addAttribute("book", item);
        return VIEW_PATH + "book/editForm";
    }

    @PostMapping("/{itemId}/edit/book")
    public String editBook(@PathVariable Long itemId, @Validated @ModelAttribute("book") BookUpdateForm form, BindingResult bindingResult) {
        //특정 필드 예외가 아닌 전체 예외
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return VIEW_PATH + "book/editForm";
        }

        itemService.update(itemId, form);
        return "redirect:/manager/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit/food")
    public String editFoodForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findItem(itemId);
        model.addAttribute("food", item);
        return VIEW_PATH + "food/editForm";
    }

    @PostMapping("/{itemId}/edit/food")
    public String editFood(@PathVariable Long itemId, @Validated @ModelAttribute("food") FoodUpdateForm form, BindingResult bindingResult) {
        //특정 필드 예외가 아닌 전체 예외
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return VIEW_PATH + "food/editForm";
        }

        itemService.update(itemId, form);
        return "redirect:/manager/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit/movie")
    public String editMovieForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findItem(itemId);
        model.addAttribute("movie", item);
        return VIEW_PATH + "movie/editForm";
    }

    @PostMapping("/{itemId}/edit/movie")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("movie") MovieUpdateForm form, BindingResult bindingResult) {
        //특정 필드 예외가 아닌 전체 예외
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return VIEW_PATH + "movie/editForm";
        }

        itemService.update(itemId, form);
        return "redirect:/manager/items/{itemId}";
    }

    @GetMapping("{itemId}/delete")
    public String deleteItem(@PathVariable Long itemId) {
        log.info("itemId={}", itemId);
        itemService.deleteItem(itemId);
        return "redirect:/manager/items";
    }
}