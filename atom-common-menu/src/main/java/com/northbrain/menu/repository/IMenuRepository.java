package com.northbrain.menu.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.northbrain.menu.model.Menu;

import reactor.core.publisher.Mono;

public interface IMenuRepository extends ReactiveCrudRepository<Menu, String> {
    Mono<Menu> findByTypeAndRoleId(String type, String roleId);
}
