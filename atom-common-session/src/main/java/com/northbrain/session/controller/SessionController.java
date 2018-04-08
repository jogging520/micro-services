package com.northbrain.session.controller;

import com.northbrain.session.service.SessionService;
import com.northbrain.session.model.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public Mono<ResponseEntity<?>> create(@PathVariable String channelType,
                                          @PathVariable String user,
                                          @PathVariable String role,
                                          @PathVariable String organization) {
        return this.sessionService.saveSession(channelType, user, role, organization)
                .map(token -> ResponseEntity.ok().contentLength(token.length())
                        .body(token)
                );
    }
}
