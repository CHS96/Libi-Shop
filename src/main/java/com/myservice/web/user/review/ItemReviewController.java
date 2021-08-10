package com.myservice.web.user.review;

import com.myservice.domain.item.Item;
import com.myservice.domain.item.book.BookService;
import com.myservice.domain.member.Member;
import com.myservice.domain.review.ItemReview;
import com.myservice.domain.review.ItemReviewService;
import com.myservice.web.login.LoginForm;
import com.myservice.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/add")
    public String reviewForm(@ModelAttribute("form") ItemReviewForm form) {
        return "user/review/reviewForm";
    }

    @PostMapping("/add")
    public String addReview(@Validated @ModelAttribute("form") ItemReviewForm form, BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "user/review/itemReview";
        }

        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        ItemReview itemReview = ItemReview.createItemReview(form.getTitle(), form.getMessage(), form.getStar(), user);
        Item item = bookService.findItem(form.getItemId());
        itemReview.setMember(user);

        itemReviewService.save(itemReview, item);

        redirectAttributes.addAttribute("itemId", item.getId());

        return "redirect:/user/review/{itemId}";
    }
}
