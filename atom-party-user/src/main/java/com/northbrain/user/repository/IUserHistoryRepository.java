package com.northbrain.user.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.northbrain.user.model.UserHistory;

public interface IUserHistoryRepository extends ReactiveCrudRepository<UserHistory, String> {
}
