package com.northbrain.strategy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.northbrain.strategy.model.Constants;
import com.northbrain.strategy.model.Strategy;
import com.northbrain.strategy.service.StrategyService;

import reactor.core.publisher.Flux;

@RestController
public class StrategyController {

    private final StrategyService strategyService;

    public StrategyController(StrategyService strategyService) {
        this.strategyService = strategyService;
    }

    @GetMapping(Constants.STRATEGY_HTTP_REQUEST_MAPPING)
    public Flux<Strategy> listing() {
        return this.strategyService
                .selectStrategies();
    }

}
