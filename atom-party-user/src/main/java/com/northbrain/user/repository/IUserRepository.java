package com.northbrain.user.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.northbrain.user.model.User;

import reactor.core.publisher.Mono;

public interface IUserRepository extends ReactiveCrudRepository<User, String> {
    Mono<User> findByUserIdAndPassword(String userId, String password);

    Mono<User> findByUserNameAndPassword(String userName, String password);

    Mono<User> findByMobilesContaining(String mobile);
}
