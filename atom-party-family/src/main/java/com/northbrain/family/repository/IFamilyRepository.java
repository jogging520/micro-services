package com.northbrain.family.repository;

import com.northbrain.family.model.Family;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IFamilyRepository extends ReactiveCrudRepository<Family, String> {
    Mono<Family> findByCategoryAndHouseHolderAndPhone(String category, String houseHolder, String phone);
}
