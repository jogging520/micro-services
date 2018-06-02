package com.northbrain.show.repository;

import com.northbrain.show.model.Show;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IShowRepository extends ReactiveCrudRepository<Show, String> {
    Flux<Show> findByStatus(String status);
}
