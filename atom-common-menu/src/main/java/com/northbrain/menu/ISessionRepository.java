package com.northbrain.menu;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ISessionRepository extends ReactiveCrudRepository<Session, String> {
    Mono<Session> findByChannelTypeAndUserId(String channelType, String userId);

    Mono<Session> findBySessionId(String sessionId);
}
