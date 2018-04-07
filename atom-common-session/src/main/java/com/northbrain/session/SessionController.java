package com.northbrain.session;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/sessions")
    public Flux<Session> listing() {
        return this.sessionService.findAllSessions();
    }

    @PostMapping("/sessions")
    public Mono<Void> create(@RequestBody Flux<Session> sessions) {
        return sessions
                .flatMap(session -> {
                    Mono<Session> saveSession = this.sessionService.saveSession(session);
                    return saveSession;
                })
                .then();
    }

}
