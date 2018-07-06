package com.northbrain.session.repository;

import com.northbrain.session.model.AttemptHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IAttemptHistoryRepository  extends ReactiveCrudRepository<AttemptHistory, String> {
}
