package com.northbrain.solution.repository;

import com.northbrain.solution.model.Solution;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ISolutionRepository extends ReactiveCrudRepository<Solution, String> {
    Flux<Solution> findByStatus(String status);
}
