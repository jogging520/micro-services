package com.northbrain.menu.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.northbrain.menu.model.MenuHistory;

public interface IMenuHistoryRepository extends ReactiveCrudRepository<MenuHistory, String> {
}
