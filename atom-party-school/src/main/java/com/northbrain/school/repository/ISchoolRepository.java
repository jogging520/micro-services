package com.northbrain.school.repository;

import com.northbrain.school.model.School;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISchoolRepository extends ReactiveCrudRepository<School, String> {
    Flux<School> findByRegionIn(String[] regions);

    Flux<School> findByNameLike(String name);

    Mono<School> findByName(String name);
}
