package com.northbrain.session.repository;

import com.northbrain.session.model.Attempt;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IAttemptRepository extends ReactiveCrudRepository<Attempt, String> {
    Flux<Attempt> findByUserNameAndAppTypeAndAttemptTimeBetween(String userName,
                                                                String appType,
                                                                Date fromAttemptTime,
                                                                Date toAttemptTime);

    Flux<Attempt> findAllByUserNameAndAppType(String userName, String appType);
}
