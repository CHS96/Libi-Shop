package com.myservice.domain.item.movie;

import com.myservice.domain.item.Item;
import com.myservice.domain.item.ItemRepository;
import com.myservice.web.manager.items.movie.MovieSaveForm;
import com.myservice.web.manager.items.movie.MovieUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {

    private final ItemRepository itemRepository;

    public Long save(MovieSaveForm form) {
        Movie movie = Movie.createMovie(form.getItemName(), form.getPrice(), form.getQuantity(), form.getGenre());
        itemRepository.save(movie);
        return movie.getId();
    }

    public Long update(Long itemId, MovieUpdateForm form) {
        Movie movie = (Movie) itemRepository.findById(itemId).get();
        movie.updateMovie(form);
        return itemId;
    }

    @Transactional(readOnly = true)
    public Item findItem(Long itemId) {
        return itemRepository.findById(itemId).get();
    }
}