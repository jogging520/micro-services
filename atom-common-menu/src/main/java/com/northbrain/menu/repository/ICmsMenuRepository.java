package com.northbrain.menu.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.northbrain.menu.model.CmsMenu;

import reactor.core.publisher.Mono;

public interface ICmsMenuRepository extends ReactiveCrudRepository<CmsMenu, String> {
}
