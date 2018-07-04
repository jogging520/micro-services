package com.northbrain.storage.repository;

import com.northbrain.storage.model.Picture;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IPictureRepository extends ReactiveCrudRepository<Picture, String> {
    Flux<Picture> findByStatus(String status);
}
