package com.northbrain.organization.repository;

import com.northbrain.organization.model.RegionHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IRegionHistoryRepository extends ReactiveCrudRepository<RegionHistory, String> {
}
