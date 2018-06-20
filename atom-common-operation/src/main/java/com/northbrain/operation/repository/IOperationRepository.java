package com.northbrain.operation.repository;

import com.northbrain.operation.model.Operation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IOperationRepository extends ReactiveCrudRepository<Operation, String> {
    Flux<Operation> findByUserIdAndStatus(String userId, String status);
}
