package com.northbrain.operation.service;

import com.northbrain.operation.model.Constants;
import com.northbrain.operation.model.Operation;
import com.northbrain.operation.repository.IOperationRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OperationService {
    private final IOperationRepository operationRepository;

    public OperationService(IOperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public Flux<Operation> queryOperations(String userId) {
        return this.operationRepository
                .findByUserIdAndStatus(userId, Constants.OPERATION_STATUS_ACTIVE);
    }

    public Mono<Operation> createOperation(Operation operation) {
        return this.operationRepository
                .save(operation);
    }
}
