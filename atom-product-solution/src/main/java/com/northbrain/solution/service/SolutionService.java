package com.northbrain.solution.service;

import com.northbrain.solution.model.Solution;
import com.northbrain.solution.repository.ISolutionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class SolutionService {
    private final ISolutionRepository solutionRepository;

    public SolutionService(ISolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }

    public Flux<Solution> querySolutions(String operationId) {
        return this.solutionRepository
                .findAll()
                .log(operationId);
    }
}
