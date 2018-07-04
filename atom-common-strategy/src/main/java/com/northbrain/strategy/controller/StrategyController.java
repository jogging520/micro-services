package com.northbrain.strategy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 方法：查询应用程序策略信息
     * @param serialNo 流水号
     * @return 应用程序策略列表
     */
    @GetMapping(Constants.STRATEGY_APPLICATION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Strategy>> queryApplicationStrategies(@RequestParam String serialNo) {
        return ResponseEntity.ok()
                .body(this.strategyService
                        .queryApplicationStrategies(serialNo));
    }

    /**
     * 方法：查询错误码策略信息
     * @param serialNo 流水号
     * @return 错误码策略列表
     */
    @GetMapping(Constants.STRATEGY_ERRORCODE_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Strategy>> queryErrorCodeStrategies(@RequestParam String serialNo) {
        return ResponseEntity.ok()
                .body(this.strategyService
                        .queryErrorCodeStrategies(serialNo));
    }

    /**
     * 方法：创建策略
     * @param serialNo 流水号
     * @param strategies 策略
     * @return 创建成功的策略
     */
    @PostMapping(Constants.STRATEGY_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Strategy>> createStrategies(@RequestParam String serialNo,
                                                           @RequestBody Flux<Strategy> strategies) {
        return ResponseEntity.ok()
                .body(this.strategyService
                        .createStrategies(serialNo, strategies));
    }

}
