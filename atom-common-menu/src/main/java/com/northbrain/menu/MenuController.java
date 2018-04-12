package com.northbrain.menu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class MenuController {
    private final ISessionRepository sessionRepository;

    public MenuController(ISessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @GetMapping("/sessions")
    public Flux<Session> listing() {
        return this.sessionRepository.findAll()
                .map(session -> {
                    System.out.println(session);
                    return session;
                });
    }
}
