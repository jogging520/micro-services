package com.northbrain.search.repository;

import com.northbrain.search.model.Search;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ISearchRepository extends ReactiveCrudRepository<Search, String> {
}
