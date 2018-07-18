package com.northbrain.operation.repository;

import com.northbrain.operation.model.Operation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IOperationRepository extends ReactiveCrudRepository<Operation, String> {
    Flux<Operation> findByCategoryAndStatusAndUser(String category,
                                                   String status,
                                                   String user);

    Flux<Operation> findByCategoryAndStatusAndCreateTimeBetween(String category,
                                                                String status,
                                                                Date fromCreateTime,
                                                                Date toCreateDate);

    Flux<Operation> findByCategoryAndStatusAndUserAndCreateTimeBetween(String category,
                                                                       String status,
                                                                       String user,
                                                                       Date fromCreateTime,
                                                                       Date toCreateDate);
}
