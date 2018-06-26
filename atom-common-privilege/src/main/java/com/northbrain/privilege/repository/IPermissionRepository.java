package com.northbrain.privilege.repository;

import com.northbrain.privilege.model.Permission;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IPermissionRepository extends ReactiveCrudRepository<Permission, Integer> {
}
