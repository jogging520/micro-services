package com.northbrain.organization.repository;

import com.northbrain.organization.model.Region;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IRegionRepository extends ReactiveCrudRepository<Region, String> {
}
