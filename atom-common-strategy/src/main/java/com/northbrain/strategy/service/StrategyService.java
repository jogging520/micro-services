package com.northbrain.strategy.service;

import org.springframework.stereotype.Service;

import com.northbrain.strategy.model.Constants;
import com.northbrain.strategy.model.Strategy;
import com.northbrain.strategy.repository.IStrategyRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class StrategyService {

    private final IStrategyRepository strategyRepository;

    public StrategyService(IStrategyRepository strategyRepository) {
        this.strategyRepository = strategyRepository;
    }

    public Flux<Strategy> selectStrategies() {
        return this.strategyRepository
                .findAll()
                .filter(strategy -> strategy.getStatus().equalsIgnoreCase(Constants.STRATEGY_STATUS_ACTIVE));
    }

    public Flux<Strategy> saveStrategies(Flux<Strategy> strategies) {
        return this.strategyRepository.saveAll(strategies);
    }
}
