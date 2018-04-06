package com.northbrain.session;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ISessionRepository extends ReactiveCrudRepository<Session, String> {
}
