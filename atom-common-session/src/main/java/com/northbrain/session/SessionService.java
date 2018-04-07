package com.northbrain.session;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SessionService {
    private final ISessionRepository sessionRepository;
    private final JsonWebToken jsonWebToken;

    public SessionService(ISessionRepository sessionRepository, JsonWebToken jsonWebToken) {
        this.sessionRepository = sessionRepository;
        this.jsonWebToken = jsonWebToken;
    }

    public Flux<Session> findAllSessions() {
        return this.sessionRepository.findAll().log(jsonWebToken.toString());
    }

    public Mono<Session> saveSession(Session session) {
        return this.sessionRepository.save(session);
    }
}
