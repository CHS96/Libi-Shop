package com.myservice.web.user.review;

import com.myservice.domain.item.Item;
import com.myservice.domain.item.book.BookService;
import com.myservice.domain.review.ItemReview;
import com.myservice.domain.review.ItemReviewService;
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
@RequiredArgsConstructor
@RequestMapping("/user/review")
public class ItemReviewController {

    private final BookService bookService;
    private final ItemReviewService itemReviewService;

    @GetMapping("/{itemId}")
    public String review(@PathVariable Long itemId, Model model) {
        Item item = bookService.findItem(itemId);
        List<ItemReview> reviews = itemReviewService.findAll(item);

        model.addAttribute("item", item);
        model.addAttribute("reviews", reviews);

        return "user/review/itemReview";
    }

    @GetMapping("/{itemId}/add")
    public String addReview(@PathVariable Long itemId, Model model) {
        return "user/review/itemReview";
    }
}
