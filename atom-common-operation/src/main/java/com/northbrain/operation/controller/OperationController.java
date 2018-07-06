package com.northbrain.operation.controller;

import com.northbrain.operation.model.Constants;
import com.northbrain.operation.model.Operation;
import com.northbrain.operation.model.Record;
import com.northbrain.operation.service.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
public class OperationController {
    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    /**
     * 方法：根据各类组合信息查询操作记录
     * @param serialNo 流水号
     * @param user 用户信息
     * @param fromCreateTime 查询开始时间
     * @param toCreateTime 查询结束时间
     * @return 操作记录
     */
    @GetMapping(Constants.OPERATION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Operation>> queryOperations(@RequestParam String serialNo,
                                                           @RequestParam(required = false) String user,
                                                           @RequestParam(required = false) Date fromCreateTime,
                                                           @RequestParam(required = false) Date toCreateTime) {
        if(user != null && fromCreateTime != null && toCreateTime != null)
            return ResponseEntity.ok()
                    .body(this.operationService
                            .queryOperationByUserAndCreateTime(serialNo, user,
                                    fromCreateTime, toCreateTime));

        if(user == null && fromCreateTime != null && toCreateTime != null)
            return ResponseEntity.ok()
                    .body(this.operationService
                            .queryOperationByCreateTime(serialNo, fromCreateTime, toCreateTime));

        if(user != null && fromCreateTime == null && toCreateTime == null)
            return ResponseEntity.ok()
                    .body(this.operationService
                            .queryOperationsByUser(serialNo, user));

        return ResponseEntity.badRequest().body(null);
    }

    /**
     * 方法：创建一条操作记录
     * @param operation 操作记录
     * @return 创建成功的操作记录
     */
    @PostMapping(Constants.OPERATION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Operation>> createOperation(@RequestBody Operation operation) {
        return ResponseEntity.ok()
                .body(this.operationService
                        .createOperation(operation));
    }

    /**
     * 方法：根据流水号查找操作记录
     * @param serialNo 流水号
     * @return 操作记录
     */
    @GetMapping(Constants.OPERATION_SPECIFIED_HTTP_REQUEST_MAPPING)
    private ResponseEntity<Mono<Operation>> queryOperationById(@RequestParam String serialNo) {
        return ResponseEntity.ok()
                .body(this.operationService
                        .queryOperationById(serialNo));
    }

    /**
     * 方法：根据流水号查找操作详细记录
     * @param serialNo 流水号
     * @return 操作详细记录
     */
    @GetMapping(Constants.OPERATION_RECORD_HTTP_REQUEST_MAPPING)
    private ResponseEntity<Flux<Record>> queryRecordsBySerialNo(@RequestParam String serialNo) {
        return ResponseEntity.ok()
                .body(this.operationService
                        .queryRecordsBySerialNo(serialNo));
    }

    /**
     * 方法：创建新记录（操作明细）
     * @param record 明细记录
     * @return 创建成功的明细记录
     */
    @PostMapping(Constants.OPERATION_RECORD_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Record>> createRecord(@RequestBody Record record) {
        return ResponseEntity.ok()
                .body(this.operationService
                        .createRecord(record));
    }
}
