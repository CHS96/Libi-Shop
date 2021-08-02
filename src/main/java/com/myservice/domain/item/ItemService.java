package com.myservice.domain.item;

import com.myservice.domain.itemBasket.ItemBasket;
import com.myservice.domain.member.User;
import com.myservice.web.manager.items.book.BookSaveForm;
import com.myservice.web.manager.items.ItemSaveForm;
import com.myservice.web.manager.items.ItemUpdateForm;
import com.myservice.web.manager.items.book.BookUpdateForm;
import com.myservice.web.manager.items.food.FoodSaveForm;
import com.myservice.web.manager.items.food.FoodUpdateForm;
import com.myservice.web.manager.items.movie.MovieSaveForm;
import com.myservice.web.manager.items.movie.MovieUpdateForm;
import com.myservice.web.user.items.ItemForm;
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
        } else {
            Movie movie = Movie.createMovie(form.getItemName(), form.getPrice(), form.getQuantity(), ((MovieSaveForm) form).getGenre());
            itemRepository.save(movie);
            return movie.getId();
        }
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

    public void removeItem(Long itemId) {
        itemRepository.delete(itemId);
    }

    @Transactional(readOnly = true)
    public Item findItem(Long itemId) {
        return itemRepository.findById(itemId).get();
    }

    @Transactional(readOnly = true)
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    /**
     * 장바구니 Item 추가
     */
    public void addItemBasket(User user, Long itemId, int count) {
        //Item 재고 감소
        Item item = itemRepository.findById(itemId).get();
        item.removeStock(count);

        if (user.getItemBasket() == null) {
            ItemBasket itemBasket = new ItemBasket();
            user.setItemBasket(itemBasket);
            itemBasket.setMember(user);
            itemBasket.setItems(user.getItemBasket().getItems());
        }
        ItemBasket itemBasket = user.getItemBasket();
        //장바구니에 Item 추가
        Item newItem = new Item();
        newItem.setItemName(item.getItemName());
        newItem.setItemType(item.getItemType());
        newItem.setPrice(item.getTotalPrice(count));
        newItem.setQuantity(count);

        itemBasket.addItem(newItem);
        itemBasket.addItem(newItem);
        itemBasket.addItem(newItem);

        itemRepository.saveItem(itemBasket);
    }

    public List<Item> findItemsInItemBasket(User user) {
        return user.getItemBasket().getItems();
    }
}