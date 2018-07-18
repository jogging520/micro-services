package com.northbrain.organization.repository;

import com.northbrain.organization.model.Region;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

public interface IRegionRepository extends ReactiveCrudRepository<Region, String> {
    Flux<Region> findByCategory(String category);
}
