package com.northbrain.school.repository;

import com.northbrain.school.model.School;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ISchoolRepository extends ReactiveCrudRepository<School, String> {
    Flux<School> findByRegionIdIn(String[] regionIds);

    Flux<School> findByNameLike(String name);
}
