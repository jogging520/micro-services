package com.northbrain.operation.repository;

import com.northbrain.operation.model.Record;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IRecordRepository extends ReactiveCrudRepository<Record, String> {
    Flux<Record> findByCategoryAndStatusAndSerialNo(String category, String status, String serialNo);
}
