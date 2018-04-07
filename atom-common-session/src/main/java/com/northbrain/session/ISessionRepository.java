package com.northbrain.session;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ISessionRepository extends ReactiveCrudRepository<Session, String> {
    Mono<Session> findByTypeAndUserId(String type, String userId);
}
