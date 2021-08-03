package com.myservice.web.manager.items.movie;

import com.myservice.domain.item.*;
import com.myservice.domain.item.book.Book;
import com.myservice.domain.item.movie.Movie;
import com.myservice.domain.item.movie.MovieService;
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
public class MovieController {

    private final MovieService movieService;

    private final String VIEW_PATH = "manager/items/";

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return CreateItemTypeValues.getInstance();
    }

    @GetMapping()
    public String items(Model model) {
        List<Item> items = movieService.findItems();
        model.addAttribute("items", items);
        return VIEW_PATH + "items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = movieService.findItem(itemId);
        ItemType itemType = item.getItemType();
        model.addAttribute("item", item);

        return VIEW_PATH + "movie/item";
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

        return "redirect:/manager/items/addMovie";
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

        Long savedId = movieService.save(form);
        redirectAttributes.addAttribute("itemId", savedId);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/manager/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit/movie")
    public String editMovieForm(@PathVariable Long itemId, Model model) {
        Item item = movieService.findItem(itemId);
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

        movieService.update(itemId, form);
        return "redirect:/manager/items/{itemId}";
    }

    @GetMapping("{itemId}/delete")
    public String deleteItem(@PathVariable Long itemId) {
        movieService.removeItem(itemId);
        return "redirect:/manager/items";
    }
}