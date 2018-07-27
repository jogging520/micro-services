package com.northbrain.task.repository;

import com.northbrain.task.model.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IMessageRepository extends ReactiveCrudRepository<Message, String> {
    Flux<Message> findByCategoryAndStatus(String category, String status);
}
