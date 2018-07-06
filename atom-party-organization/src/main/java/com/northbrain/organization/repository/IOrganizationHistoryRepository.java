package com.northbrain.organization.repository;

import com.northbrain.organization.model.OrganizationHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IOrganizationHistoryRepository extends ReactiveCrudRepository<OrganizationHistory, String> {
}
