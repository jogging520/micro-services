package com.northbrain.strategy.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.northbrain.strategy.model.Strategy;
import reactor.core.publisher.Flux;

public interface IStrategyRepository extends ReactiveCrudRepository<Strategy, String> {
    Flux<Strategy> findByStatusAndTypeAndAppTypeAndCategory(String status, String type, String appType, String category);
}
