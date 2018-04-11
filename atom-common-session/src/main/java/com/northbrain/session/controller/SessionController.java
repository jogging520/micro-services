package com.northbrain.session.controller;

import com.northbrain.session.model.Constants;
import com.northbrain.session.service.SessionService;
import com.northbrain.session.model.Session;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping(Constants.SESSION_HTTP_REQUEST_MAPPING)
    public Flux<Session> listing() {
        return this.sessionService.findAllSessions()
                .map(session -> {
                    System.out.println(session);
                    return session;
                });
    }

    @GetMapping(Constants.SESSION_HTTP_REQUEST_MAPPING_BY_ID)
    public Mono<Session> findOne(@PathVariable String sessionId) {
        return this.sessionService
                .findOneSession(sessionId)
                .map(session -> {
                    System.out.println(session);
                    return session;
                });
    }

    @PostMapping(Constants.SESSION_HTTP_REQUEST_MAPPING)
    public Mono<String> createSession(@RequestParam String channelType,
                                      @RequestParam String userId,
                                      @RequestParam String roleId,
                                      @RequestParam String organizationId) {
        return this.sessionService
                .createSession(channelType, userId, roleId, organizationId)
                .log();
    }
}
