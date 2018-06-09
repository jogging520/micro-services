package com.northbrain.organization.repository;

import com.northbrain.organization.model.Organization;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IOrganizationRepository extends ReactiveCrudRepository<Organization, String> {
}
