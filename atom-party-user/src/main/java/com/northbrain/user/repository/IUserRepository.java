package com.northbrain.user.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.northbrain.user.model.User;

import reactor.core.publisher.Mono;

public interface IUserRepository extends ReactiveCrudRepository<User, String> {
    Mono<User> findByIdAndStatus(String id, String status);

    Mono<User> findByIdOrName(String id, String name);

    Mono<User> findByName(String name);

    Mono<User> findByNameAndPassword(String name, String password);

    Mono<User> findByMobilesContaining(String mobile);
}
