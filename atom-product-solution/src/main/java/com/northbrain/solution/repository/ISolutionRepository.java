package com.northbrain.solution.repository;

import com.northbrain.solution.model.Solution;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ISolutionRepository extends ReactiveCrudRepository<Solution, String> {
}
