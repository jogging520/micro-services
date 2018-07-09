package com.northbrain.search.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.northbrain.search.model.Constants;
import com.northbrain.search.model.Summary;
import com.northbrain.search.service.SearchService;

import reactor.core.publisher.Flux;

@RestController
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping(Constants.SEARCH_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Summary>> createSummaries(@RequestParam String serialNo,
                                                         @RequestBody Flux<Summary> summaries) {
        return ResponseEntity.ok()
                .body(this.searchService
                        .createSummaries(serialNo, summaries));
    }
}
