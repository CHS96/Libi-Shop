package com.myservice.domain.item;

import com.myservice.web.manager.items.book.BookSaveForm;
import com.myservice.web.manager.items.ItemSaveForm;
import com.myservice.web.manager.items.ItemUpdateForm;
import com.myservice.web.manager.items.book.BookUpdateForm;
import com.myservice.web.manager.items.food.FoodSaveForm;
import com.myservice.web.manager.items.food.FoodUpdateForm;
import com.myservice.web.manager.items.movie.MovieSaveForm;
import com.myservice.web.manager.items.movie.MovieUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public Long save(ItemSaveForm form) {
        if (form instanceof BookSaveForm) {
            Book book = Book.createBook(form.getItemName(), form.getPrice(), form.getQuantity(), ((BookSaveForm) form).getAuthor());
            itemRepository.save(book);
            return book.getId();
        } else if (form instanceof FoodSaveForm) {
            Food food = Food.createFood(form.getItemName(), form.getPrice(), form.getQuantity(), ((FoodSaveForm) form).getFoodType());
            itemRepository.save(food);
            return food.getId();
        } else if (form instanceof MovieSaveForm) {
            Movie movie = Movie.createMovie(form.getItemName(), form.getPrice(), form.getQuantity(), ((MovieSaveForm) form).getGenre());
            itemRepository.save(movie);
            return movie.getId();
        }
        return null;
    }

    public Long update(Long itemId, ItemUpdateForm form) {
        if (form instanceof BookUpdateForm) {
            Book book = (Book) itemRepository.findById(itemId).get();
            book.setItemName(form.getItemName());
            book.setPrice(form.getPrice());
            book.setQuantity(form.getQuantity());
            book.setAuthor(((BookUpdateForm) form).getAuthor());
        } else if (form instanceof FoodUpdateForm) {
            Food food = (Food) itemRepository.findById(itemId).get();
            food.setItemName(form.getItemName());
            food.setPrice(form.getPrice());
            food.setQuantity(form.getQuantity());
            food.setFoodType(((FoodUpdateForm) form).getFoodType());
        } else if (form instanceof MovieUpdateForm) {
            Movie movie = (Movie) itemRepository.findById(itemId).get();
            movie.setItemName(form.getItemName());
            movie.setPrice(form.getPrice());
            movie.setQuantity(form.getQuantity());
            movie.setGenre(((MovieUpdateForm) form).getGenre());
        }
        return itemId;
    }

    @Transactional(readOnly = true)
    public Item findItem(Long itemId) {
        return itemRepository.findById(itemId).get();
    }

    @Transactional(readOnly = true)
    public List<Item> findItems() {
        return itemRepository.findAll();
    }
}
