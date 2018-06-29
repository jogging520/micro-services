package com.northbrain.family.repository;

import com.northbrain.family.model.Family;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IFamilyRepository extends ReactiveCrudRepository<Family, String> {
}
