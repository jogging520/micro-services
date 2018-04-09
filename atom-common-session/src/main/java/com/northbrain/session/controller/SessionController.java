package com.northbrain.session.controller;

import com.northbrain.session.model.Constants;
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

    //@GetMapping(Constants.SESSION_HTTP_REQUEST_MAPPING)
    //public Flux<Session> listing() {
    //    return this.sessionService.findAllSessions();
    //}

    @GetMapping(Constants.SESSION_HTTP_REQUEST_MAPPING)
    public Mono<ResponseEntity<?>> create(@RequestParam("channelType") String channelType,
                                          @RequestParam("userId") String userId,
                                          @RequestParam("roleId") String roleId,
                                          @RequestParam("organizationId") String organizationId) {
        return this.sessionService.saveSession(channelType, userId, roleId, organizationId)

                .map(token -> ResponseEntity.ok().contentLength(token.length())
                        .body(token)
                );
    }
}
