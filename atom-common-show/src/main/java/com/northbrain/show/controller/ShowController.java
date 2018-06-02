package com.northbrain.show.controller;

import com.northbrain.show.model.Constants;
import com.northbrain.show.model.Show;
import com.northbrain.show.service.ShowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ShowController {
    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping(Constants.SHOW_HTTP_REQUEST_MAPPING)
    public Flux<Show> listing() {
        return this.showService
                .listing();
    }

    @PostMapping(Constants.SHOW_HTTP_REQUEST_MAPPING)
    public Flux<Show> createNewShow(@RequestBody  Flux<Show> shows) {
        return this.showService
                .createNewShow(shows);
    }
}
