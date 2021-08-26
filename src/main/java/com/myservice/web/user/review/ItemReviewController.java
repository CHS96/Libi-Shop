package com.myservice.web.user.review;

import com.myservice.domain.item.Item;
import com.myservice.domain.item.book.BookService;
import com.myservice.domain.member.Member;
import com.myservice.domain.review.ItemReview;
import com.myservice.domain.review.ItemReviewService;
import com.myservice.web.paging.Paging;
import com.myservice.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    private final String VIEW_PATH = "user/review/";

    @GetMapping("/{itemId}/page/{pageIndex}")
    public String review(@PathVariable Long itemId, @PathVariable int pageIndex, Model model) {
        Item item = bookService.findItem(itemId);
        List<ItemReview> reviews = itemReviewService.findReviewsByPagingOfItem(item, (pageIndex - 1) * Paging.MAX_SIZE);
        Long totalSize = itemReviewService.findReviewsTotalSizeOfItem(item);

        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("maxSize", Paging.MAX_SIZE);
        model.addAttribute("totalSize", totalSize);
        model.addAttribute("itemId", itemId);
        model.addAttribute("reviews", reviews);

        return VIEW_PATH + "itemReviews";
    }

    @GetMapping("/userReviews/page/{pageIndex}")
    public String userReviews(@PathVariable int pageIndex, HttpSession session, Model model) {
        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<ItemReview> reviews = itemReviewService.findReviewsByPagingOfUser(user, (pageIndex - 1) * Paging.MAX_SIZE);
        Long totalSize = itemReviewService.findReviewsTotalSizeOfUser(user);

        model.addAttribute("reviews", reviews);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("maxSize", Paging.MAX_SIZE);
        model.addAttribute("totalSize", totalSize);

        return VIEW_PATH + "userReviews";
    }

    @GetMapping("/add")
    public String reviewForm(@ModelAttribute("form") ItemReviewForm form) {
        return VIEW_PATH + "reviewForm";
    }

    @PostMapping("/add")
    public String addReview(@Validated @ModelAttribute("form") ItemReviewForm form, BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return VIEW_PATH + "reviewForm";
        }

        Long itemId = form.getItemId();
        Item item = bookService.findItem(itemId);
        if (item == null) {
            bindingResult.addError(new FieldError("itemId", "itemId", "존재하지 않는 상품 ID 입니다."));
            return VIEW_PATH + "reviewForm";
        }

        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        ItemReview itemReview = ItemReview.createItemReview(form.getTitle(), form.getContent(), form.getStar(), user);

        itemReviewService.save(itemReview, item);
        redirectAttributes.addAttribute("itemId", item.getId());

        return "redirect:/user/review/{itemId}/page/1";
    }

    @GetMapping("/content/{itemReviewId}")
    public String content(@PathVariable Long itemReviewId, Model model) {
        ItemReview itemReview = itemReviewService.findOne(itemReviewId, true);
        Item item = itemReview.getItem();

        model.addAttribute("review", itemReview);
        model.addAttribute("item", item);

        return VIEW_PATH + "itemReview";
    }

    @GetMapping("/edit/{reviewId}")
    public String editForm(@PathVariable Long reviewId, Model model) {
        ItemReview review = itemReviewService.findOne(reviewId, false);
        Item item = review.getItem();

        model.addAttribute("review", review);
        model.addAttribute("item", item);

        return VIEW_PATH + "updateForm";
    }

    @PostMapping("/edit/{reviewId}")
    public String editReview(@PathVariable Long reviewId, @Validated @ModelAttribute("review") ItemReviewUpdateForm form,
                             BindingResult bindingResult, @ModelAttribute("item") Item item) {
        if (bindingResult.hasErrors()) {
            return VIEW_PATH + "updateForm";
        }

        itemReviewService.update(reviewId, form);
        return "redirect:/user/review/userReviews/page/1";
    }

    @GetMapping("/delete/{reviewId}")
    public String delete(@PathVariable Long reviewId) {
        ItemReview review = itemReviewService.findOne(reviewId, false);
        itemReviewService.remove(review);
        return "redirect:/user/review/userReviews/page/1";
    }
}
