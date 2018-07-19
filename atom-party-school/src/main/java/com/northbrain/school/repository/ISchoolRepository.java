package com.northbrain.school.repository;

import com.northbrain.school.model.School;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISchoolRepository extends ReactiveCrudRepository<School, String> {
    Flux<School> findByCategoryAndRegionIn(String category, String[] regions);

    Flux<School> findByCategoryAndNameLike(String category, String name);

    Mono<School> findByCategoryAndName(String category, String name);
}
