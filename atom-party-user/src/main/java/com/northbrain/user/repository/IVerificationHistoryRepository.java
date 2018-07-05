package com.northbrain.user.repository;

import com.northbrain.user.model.VerificationHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IVerificationHistoryRepository extends ReactiveCrudRepository<VerificationHistory, String> {
}
