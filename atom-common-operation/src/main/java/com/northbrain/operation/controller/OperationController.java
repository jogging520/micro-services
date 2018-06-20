package com.northbrain.operation.controller;

import com.northbrain.operation.model.Constants;
import com.northbrain.operation.model.Operation;
import com.northbrain.operation.service.OperationService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class OperationController {
    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping(Constants.OPERATION_HTTP_REQUEST_MAPPING)
    public Flux<Operation> queryOperations(@RequestParam String userId) {
        return this.operationService
                .queryOperations(userId);
    }

    @PostMapping(Constants.OPERATION_HTTP_REQUEST_MAPPING)
    public Mono<Operation> createOperation(@RequestBody Operation operation) {
        System.out.println("==" + operation.getAppType() + "===" + operation.getBusinessType());

        return this.operationService
                .createOperation(operation);
    }
}
