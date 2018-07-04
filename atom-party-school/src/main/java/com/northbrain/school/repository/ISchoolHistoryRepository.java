package com.northbrain.school.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.northbrain.school.model.SchoolHistory;

public interface ISchoolHistoryRepository extends ReactiveCrudRepository<SchoolHistory, String> {
}
