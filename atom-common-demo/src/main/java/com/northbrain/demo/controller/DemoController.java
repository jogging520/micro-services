package com.northbrain.demo.controller;

import com.northbrain.demo.model.Constants;
import com.northbrain.demo.model.Girl;
import com.northbrain.demo.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class DemoController {
    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @PostMapping(Constants.DEMO_HTTP_REQUEST_MAPPING)
    public Mono<Girl> createNewGirl(@RequestBody Girl girl) {
        return this.demoService
                .createNewGirl(girl.getFirstName(),
                        girl.getLastName(),
                        girl.getMobiles()
                        );
    }

    @GetMapping(Constants.DEMO_HTTP_REQUEST_MAPPING)
    public Flux<Girl> listing() {
        return this.demoService
                .listing();
    }
}
