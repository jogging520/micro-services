package com.northbrain.organization.repository;

import com.northbrain.organization.model.Organization;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IOrganizationRepository extends ReactiveCrudRepository<Organization, String> {
}
