package com.northbrain.user.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.northbrain.user.model.User;

import reactor.core.publisher.Mono;

public interface IUserRepository extends ReactiveCrudRepository<User, String> {
    Mono<User> findByCategoryAndStatusAndIdOrName(String category,
                                                  String status,
                                                  String id,
                                                  String name);

    Mono<User> findByCategoryAndStatusAndName(String category,
                                              String status,
                                              String name);

    Mono<User> findByCategoryAndStatusAndNameAndPassword(String category,
                                                         String status,
                                                         String name,
                                                         String password);

    Mono<User> findByCategoryAndStatusAndMobilesContaining(String category,
                                                           String status,
                                                           String mobile);
}
