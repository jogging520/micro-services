package com.northbrain.privilege.repository;

import com.northbrain.privilege.model.Role;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IRoleRepository extends ReactiveCrudRepository<Role, String> {
    Flux<Role> findByIdInAndAppTypesContainingAndCategory(String[] ids, String appType, String category);
}
