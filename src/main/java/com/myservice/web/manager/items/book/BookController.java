package com.myservice.web.manager.items.book;

import com.myservice.domain.item.*;
import com.myservice.domain.item.book.Book;
import com.myservice.domain.item.book.BookService;
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
public class BookController {

    private final BookService bookService;

    private final String VIEW_PATH = "manager/items/";

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return CreateItemTypeValues.getInstance();
    }

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

        return VIEW_PATH + "book/item";
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

        return "redirect:/manager/items/addBook";
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

        Long savedId = bookService.save(form);
        redirectAttributes.addAttribute("itemId", savedId);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/manager/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit/book")
    public String editBookForm(@PathVariable Long itemId, Model model) {
        Item item = bookService.findItem(itemId);
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

        bookService.update(itemId, form);
        return "redirect:/manager/items/{itemId}";
    }

    @GetMapping("{itemId}/delete")
    public String deleteItem(@PathVariable Long itemId) {
        bookService.removeItem(itemId);
        return "redirect:/manager/items";
    }
}