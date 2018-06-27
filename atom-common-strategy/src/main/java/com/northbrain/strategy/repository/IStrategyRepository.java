package com.northbrain.strategy.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.northbrain.strategy.model.Strategy;
import reactor.core.publisher.Flux;

public interface IStrategyRepository extends ReactiveCrudRepository<Strategy, String> {
    Flux<Strategy> findByTypeAndStatus(String type, String status);
}
