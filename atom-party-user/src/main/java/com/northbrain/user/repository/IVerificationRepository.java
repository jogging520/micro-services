package com.northbrain.user.repository;

import com.northbrain.user.model.Verification;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IVerificationRepository extends ReactiveCrudRepository<Verification, String> {
    Flux<Verification> findByUserAndAppType(String user, String appType);
}
