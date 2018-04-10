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

    @GetMapping("/sessions")
    public Flux<Session> listing() {
        return this.sessionService.findAllSessions()
                .map(session -> {
                    System.out.println(session);
                    return session;
                });
    }

    @GetMapping("/sessions/{sessionId}")
    public Mono<Session> findOne(@PathVariable String sessionId) {
        return this.sessionService.findOneSession(sessionId)
                .map(session -> {
                    System.out.println(session);
                    return session;
                });
    }

    @PostMapping(Constants.SESSION_HTTP_REQUEST_MAPPING)
    public Mono<String> createSession(@RequestParam("channelType") String channelType,
                                                      @RequestParam("userId") String userId,
                                                      @RequestParam("roleId") String roleId,
                                                      @RequestParam("organizationId") String organizationId) {
        return this.sessionService.createSession(channelType, userId, roleId, organizationId)
                .log();
    }
}
