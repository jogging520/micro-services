package com.northbrain.search.repository;

import com.northbrain.search.model.SearchHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ISearchHistoryRepository extends ReactiveCrudRepository<SearchHistory, String> {
}
