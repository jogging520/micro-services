package com.northbrain.strategy.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.northbrain.strategy.model.Strategy;

public interface IStrategyRepository extends ReactiveCrudRepository<Strategy, String> {
}
