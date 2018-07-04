package com.northbrain.family.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.northbrain.family.model.FamilyHistory;

public interface IFamilyHistoryRepository extends ReactiveCrudRepository<FamilyHistory, String> {
}
