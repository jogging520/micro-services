package com.northbrain.session.repository;

import com.northbrain.session.model.Attempt;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IAttemptRepository extends ReactiveCrudRepository<Attempt, String> {
    Flux<Attempt> findByUserNameAndAppTypeAndCategoryAndAttemptTimeBetween(String userName,
                                                                           String appType,
                                                                           String category,
                                                                           Date fromAttemptTime,
                                                                           Date toAttemptTime);

    Flux<Attempt> findAllByUserNameAndAppTypeAndCategory(String userName,
                                                         String appType,
                                                         String category);
}
