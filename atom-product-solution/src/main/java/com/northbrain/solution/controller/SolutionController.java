package com.northbrain.solution.controller;

import com.northbrain.solution.model.Constants;
import com.northbrain.solution.model.Solution;
import com.northbrain.solution.service.SolutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class SolutionController {
    private final SolutionService solutionService;

    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @GetMapping(Constants.SOLUTION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Solution>> querySolutions(@RequestParam String operationId) {
        return ResponseEntity.ok()
                .body(this.solutionService
                        .querySolutions(operationId));
    }
}