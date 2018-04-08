package com.northbrain.session.repository;

import com.northbrain.session.model.SessionHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ISessionHistoryRepository extends ReactiveCrudRepository<SessionHistory, String> {
}
