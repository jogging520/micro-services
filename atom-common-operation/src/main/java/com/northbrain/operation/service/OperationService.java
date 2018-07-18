package com.northbrain.operation.service;

import com.northbrain.operation.model.Constants;
import com.northbrain.operation.model.Operation;
import com.northbrain.operation.model.Record;
import com.northbrain.operation.repository.IOperationRepository;
import com.northbrain.operation.repository.IRecordRepository;
import com.northbrain.util.timer.Clock;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@Log
public class OperationService {
    private final IOperationRepository operationRepository;
    private final IRecordRepository recordRepository;

    public OperationService(IOperationRepository operationRepository, IRecordRepository recordRepository) {
        this.operationRepository = operationRepository;
        this.recordRepository = recordRepository;
    }

    /**
     * 方法：根据用户查找操作记录
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param user 用户信息
     * @return 流水记录
     */
    public Flux<Operation> queryOperationsByUser(String serialNo,
                                                 String category,
                                                 String user) {
        return this.operationRepository
                .findByCategoryAndStatusAndUser(category, Constants.OPERATION_STATUS_ACTIVE, user)
                .map(operation -> {
                    log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);
                    log.info(operation.toString());
                    return operation;
                });
    }

    /**
     * 方法：根据时间段查找操作记录
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param fromCreateTime 开始时间
     * @param toCreateTime 结束时间
     * @return 流水记录
     */
    public Flux<Operation> queryOperationByCreateTime(String serialNo,
                                                      String category,
                                                      Date fromCreateTime,
                                                      Date toCreateTime) {
        return this.operationRepository
                .findByCategoryAndStatusAndCreateTimeBetween(category, Constants.OPERATION_STATUS_ACTIVE,
                        fromCreateTime, toCreateTime)
                .map(operation -> {
                    log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);
                    log.info(operation.toString());
                    return operation;
                });

    }

    /**
     * 方法：根据用户和时间段查找操作记录
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param user 用户
     * @param fromCreateTime 开始时间
     * @param toCreateTime 结束时间
     * @return 操作记录
     */
    public Flux<Operation> queryOperationByUserAndCreateTime(String serialNo,
                                                             String category,
                                                             String user,
                                                             Date fromCreateTime,
                                                             Date toCreateTime) {
        return this.operationRepository
                .findByCategoryAndStatusAndUserAndCreateTimeBetween(category, Constants.OPERATION_STATUS_ACTIVE,
                        user, fromCreateTime, toCreateTime)
                .map(operation -> {
                    log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);
                    log.info(operation.toString());
                    return operation;
                });

    }

    /**
     * 方法：根据ID查询操作记录
     * @param serialNo 流水号
     * @return 操作记录
     */
    public Mono<Operation> queryOperationById(String serialNo) {
        return this.operationRepository
                .findById(serialNo);
    }

    /**
     * 方法：根据流水号查找详细流水记录
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @return 流水记录
     */
    public Flux<Record> queryRecordsBySerialNo(String serialNo,
                                               String category) {
        return this.recordRepository
                .findByCategoryAndStatusAndSerialNo(category, Constants.OPERATION_STATUS_ACTIVE, serialNo)
                .map(record -> {
                    log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);
                    log.info(record.toString());
                    return record;
                });
    }

    /**
     * 方法：创建操作记录
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param operation 操作记录
     * @return 创建成功的操作记录
     */
    public Mono<Operation> createOperation(String appType,
                                           String category,
                                           Operation operation) {
        log.info(operation.toString());

        return this.operationRepository
                .save(operation
                        .setAppType(appType)
                        .setCategory(category)
                        .setStatus(Constants.OPERATION_STATUS_ACTIVE)
                        .setCreateTime(Clock.currentTime())
                        .setTimestamp(Clock.currentTime()))
                .map(newOperation -> newOperation
                        .setStatus(Constants.OPERATION_ERRORCODE_SUCCESS));
    }

    /**
     * 方法：创建新记录（操作明细）
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param record 明细记录
     * @return 创建成功的明细记录
     */
    public Mono<Record> createRecord(String appType,
                                     String category,
                                     Record record) {
        log.info(record.toString());

        return this.recordRepository
                .save(record
                        .setAppType(appType)
                        .setCategory(category)
                        .setStatus(Constants.OPERATION_STATUS_ACTIVE)
                        .setCreateTime(Clock.currentTime())
                        .setTimestamp(Clock.currentTime()))
                .map(newRecord -> newRecord
                        .setStatus(Constants.OPERATION_ERRORCODE_SUCCESS));
    }
}
