package com.northbrain.operation.service;

import com.northbrain.operation.model.Constants;
import com.northbrain.operation.model.Operation;
import com.northbrain.operation.model.Record;
import com.northbrain.operation.repository.IOperationRepository;
import com.northbrain.operation.repository.IRecordRepository;
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
     * @param user 用户信息
     * @return 流水记录
     */
    public Flux<Operation> queryOperationsByUser(String serialNo,
                                                 String user) {
        return this.operationRepository
                .findByStatusAndUser(Constants.OPERATION_STATUS_ACTIVE, user)
                .map(operation -> {
                    log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);
                    log.info(operation.toString());
                    return operation;
                });
    }

    /**
     * 方法：根据时间段查找操作记录
     * @param serialNo 流水号
     * @param fromCreateTime 开始时间
     * @param toCreateTime 结束时间
     * @return 流水记录
     */
    public Flux<Operation> queryOperationByCreateTime(String serialNo,
                                                      Date fromCreateTime,
                                                      Date toCreateTime) {
        return this.operationRepository
                .findByStatusAndCreateTimeBetween(Constants.OPERATION_STATUS_ACTIVE,
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
     * @param user 用户
     * @param fromCreateTime 开始时间
     * @param toCreateTime 结束时间
     * @return 操作记录
     */
    public Flux<Operation> queryOperationByUserAndCreateTime(String serialNo,
                                                             String user,
                                                             Date fromCreateTime,
                                                             Date toCreateTime) {
        return this.operationRepository
                .findByStatusAndUserAndCreateTimeBetween(Constants.OPERATION_STATUS_ACTIVE,
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
     * @return 流水记录
     */
    public Flux<Record> queryRecordsBySerialNo(String serialNo) {
        return this.recordRepository
                .findByStatusAndSerialNo(Constants.OPERATION_STATUS_ACTIVE, serialNo)
                .map(record -> {
                    log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);
                    log.info(record.toString());
                    return record;
                });
    }

    /**
     * 方法：创建操作记录
     * @param operation 操作记录
     * @return 创建成功的操作记录
     */
    public Mono<Operation> createOperation(Operation operation) {
        log.info(operation.toString());

        return this.operationRepository
                .save(operation
                        .setStatus(Constants.OPERATION_STATUS_ACTIVE)
                        .setCreateTime(new Date())
                        .setTimestamp(new Date()))
                .map(newOperation -> newOperation
                        .setStatus(Constants.OPERATION_ERRORCODE_SUCCESS));
    }

    /**
     * 方法：创建新记录（操作明细）
     * @param record 明细记录
     * @return 创建成功的明细记录
     */
    public Mono<Record> createRecord(Record record) {
        log.info(record.toString());

        return this.recordRepository
                .save(record
                        .setStatus(Constants.OPERATION_STATUS_ACTIVE)
                        .setCreateTime(new Date())
                        .setTimestamp(new Date()))
                .map(newRecord -> newRecord
                        .setStatus(Constants.OPERATION_ERRORCODE_SUCCESS));
    }
}
