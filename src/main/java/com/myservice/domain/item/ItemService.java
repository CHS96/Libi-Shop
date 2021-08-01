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
            book = Book.updateBook(book, (BookUpdateForm) form);
        } else if (form instanceof FoodUpdateForm) {
            Food food = (Food) itemRepository.findById(itemId).get();
            food = Food.updateFood(food, (FoodUpdateForm) form);
        } else if (form instanceof MovieUpdateForm) {
            Movie movie = (Movie) itemRepository.findById(itemId).get();
            movie = Movie.updateMovie(movie, (MovieUpdateForm) form);
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

    public void deleteItem(Long itemId) {
        itemRepository.delete(itemId);
    }
}
