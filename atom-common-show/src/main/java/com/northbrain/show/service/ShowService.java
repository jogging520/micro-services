package com.northbrain.show.service;

import com.northbrain.show.model.Constants;
import com.northbrain.show.model.Show;
import com.northbrain.show.repository.IShowRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ShowService {
    private final IShowRepository showRepository;

    public ShowService(IShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public Flux<Show> listing() {
        return this.showRepository
                .findByStatus(Constants.SHOW_STATUS_ACTIVE);
    }

    public Flux<Show> createNewShow(Flux<Show> shows) {
        return shows.flatMap(show -> this.showRepository
                .save(show));
    }
}
