package com.northbrain.privilege.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.northbrain.privilege.model.RoleHistory;

public interface IRoleHistoryRepository extends ReactiveCrudRepository<RoleHistory, String> {
}
