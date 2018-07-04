package com.northbrain.strategy.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import com.northbrain.strategy.model.Constants;
import com.northbrain.strategy.model.Strategy;
import com.northbrain.strategy.repository.IStrategyRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@Log
public class StrategyService {

    private final IStrategyRepository strategyRepository;

    public StrategyService(IStrategyRepository strategyRepository) {
        this.strategyRepository = strategyRepository;
    }

    /**
     * 方法：查询应用程序策略信息
     * @param serialNo 流水号
     * @return 应用程序策略列表
     */
    public Flux<Strategy> queryApplicationStrategies(String serialNo) {
        return this.strategyRepository
                .findByStatusAndType(Constants.STRATEGY_STATUS_ACTIVE,
                        Constants.STRATEGY_TYPE_APPLICATION)
                .map(strategy -> {
                    log.info(Constants.STRATEGY_OPERATION_SERIAL_NO + serialNo);
                    log.info(strategy.toString());
                    return strategy;
                });
    }

    /**
     * 方法：查询错误码策略信息
     * @param serialNo 流水号
     * @return 错误码策略列表
     */
    public Flux<Strategy> queryErrorCodeStrategies(String serialNo) {
        return this.strategyRepository
                .findByStatusAndType(Constants.STRATEGY_STATUS_ACTIVE,
                        Constants.STRATEGY_TYPE_ERRORCODE)
                .map(strategy -> {
                    log.info(Constants.STRATEGY_OPERATION_SERIAL_NO + serialNo);
                    log.info(strategy.toString());
                    return strategy;
                });
    }

    /**
     * 方法：创建策略
     * @param serialNo 流水号
     * @param strategies 策略
     * @return 创建成功的策略
     */
    public Flux<Strategy> createStrategies(String serialNo,
                                           Flux<Strategy> strategies) {
        return strategies
                .map(strategy -> strategy.
                        setStatus(Constants.STRATEGY_STATUS_ACTIVE)
                        .setCreateTime(new Date())
                        .setTimestamp(new Date())
                        .setSerialNo(serialNo))
                .flatMap(strategy -> {
                    log.info(Constants.STRATEGY_OPERATION_SERIAL_NO + serialNo);
                    log.info(strategy.toString());
                    return this.strategyRepository
                            .save(strategy);
                });
    }
}
