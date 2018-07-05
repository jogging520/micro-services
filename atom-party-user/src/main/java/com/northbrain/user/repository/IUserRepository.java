package com.northbrain.user.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.northbrain.user.model.User;

import reactor.core.publisher.Mono;

public interface IUserRepository extends ReactiveCrudRepository<User, String> {
    Mono<User> findByIdAndStatus(String id, String status);

    Mono<User> findByIdOrUserName(String id, String userName);

    Mono<User> findByUserName(String userName);

    Mono<User> findByUserNameAndPassword(String userName, String password);

    Mono<User> findByMobilesContaining(String mobile);
}
