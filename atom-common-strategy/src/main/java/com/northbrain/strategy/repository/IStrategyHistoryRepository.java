package com.northbrain.strategy.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.northbrain.strategy.model.StrategyHistory;

public interface IStrategyHistoryRepository extends ReactiveCrudRepository<StrategyHistory, String> {
}
