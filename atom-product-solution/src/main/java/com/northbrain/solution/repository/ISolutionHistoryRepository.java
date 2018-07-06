package com.northbrain.solution.repository;

import com.northbrain.solution.model.SolutionHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ISolutionHistoryRepository extends ReactiveCrudRepository<SolutionHistory, String> {
}
