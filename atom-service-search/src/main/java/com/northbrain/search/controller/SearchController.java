package com.northbrain.search.controller;

import com.northbrain.search.model.Condition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(Constants.SEARCH_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Summary>> querySummaries(@RequestParam String serialNo,
                                                        @RequestBody Flux<Condition> conditions) {
        return ResponseEntity.ok()
                .body(this.searchService
                        .querySummaries(serialNo, conditions));

    }
}
