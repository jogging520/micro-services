package com.northbrain.demo.repository;

import com.northbrain.demo.model.Girl;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IGirlRepository extends ReactiveCrudRepository<Girl, String> {
    Flux<Girl> findByStatus(String status);
}
