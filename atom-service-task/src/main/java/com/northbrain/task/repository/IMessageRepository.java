package com.northbrain.task.repository;

import com.northbrain.task.model.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IMessageRepository extends ReactiveCrudRepository<Message, String> {
}
