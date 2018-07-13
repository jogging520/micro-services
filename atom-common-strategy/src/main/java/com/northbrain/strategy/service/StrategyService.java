package com.northbrain.strategy.service;

import com.northbrain.util.timer.Clock;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import com.northbrain.strategy.model.Constants;
import com.northbrain.strategy.model.Strategy;
import com.northbrain.strategy.model.StrategyHistory;
import com.northbrain.strategy.repository.IStrategyHistoryRepository;
import com.northbrain.strategy.repository.IStrategyRepository;

import reactor.core.publisher.Flux;

@Service
@Log
public class StrategyService {
    private final IStrategyRepository strategyRepository;
    private final IStrategyHistoryRepository strategyHistoryRepository;

    public StrategyService(IStrategyRepository strategyRepository, IStrategyHistoryRepository strategyHistoryRepository) {
        this.strategyRepository = strategyRepository;
        this.strategyHistoryRepository = strategyHistoryRepository;
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
                        .setCreateTime(Clock.currentTime())
                        .setTimestamp(Clock.currentTime())
                        .setSerialNo(serialNo))
                .flatMap(strategy -> {
                    log.info(Constants.STRATEGY_OPERATION_SERIAL_NO + serialNo);
                    log.info(strategy.toString());

                    this.strategyHistoryRepository
                            .save(StrategyHistory.builder()
                                    .operationType(Constants.STRATEGY_HISTORY_CREATE)
                                    .strategyId(strategy.getId())
                                    .type(strategy.getType())
                                    .name(strategy.getName())
                                    .parameters(strategy.getParameters())
                                    .createTime(strategy.getCreateTime())
                                    .timestamp(Clock.currentTime())
                                    .status(strategy.getStatus())
                                    .serialNo(serialNo)
                                    .description(strategy.getDescription())
                                    .build());

                    return this.strategyRepository
                            .save(strategy);
                });
    }
}
