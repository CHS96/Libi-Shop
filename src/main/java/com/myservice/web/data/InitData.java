package com.myservice.web.data;

import com.myservice.domain.item.ItemService;
import com.myservice.domain.item.book.BookService;
import com.myservice.domain.item.food.FoodService;
import com.myservice.domain.item.movie.MovieService;
import com.myservice.domain.member.Manager;
import com.myservice.domain.member.MemberService;
import com.myservice.domain.member.User;
import com.myservice.web.manager.items.book.BookSaveForm;
import com.myservice.web.manager.items.food.FoodSaveForm;
import com.myservice.web.manager.items.movie.MovieSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class InitData {

    private final BookService bookService;
    private final FoodService foodService;
    private final MovieService movieService;
    private final MemberService memberService;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        bookService.save(BookSaveForm.createBookSaveForm("Hello World", 15000, 15, "Bobby"));
        bookService.save(BookSaveForm.createBookSaveForm("Modern Java In Action", 28000, 32, "Tom"));
        foodService.save(FoodSaveForm.createFoodSaveForm("컵라면",1500, 25, "분식"));
        foodService.save(FoodSaveForm.createFoodSaveForm("돈가스",8500, 12, "분식"));
        foodService.save(FoodSaveForm.createFoodSaveForm("비빔밥",10000, 33, "한식"));
        movieService.save(MovieSaveForm.createMovieSaveForm("어벤져스", 25000, 30, "Action"));
        movieService.save(MovieSaveForm.createMovieSaveForm("Welcome", 15000, 22, "Drama"));

        memberService.save(Manager.createManager("최한슬", "manager", "manager"));
        memberService.save(User.createUser("USER", "user", "user"));
    }
}