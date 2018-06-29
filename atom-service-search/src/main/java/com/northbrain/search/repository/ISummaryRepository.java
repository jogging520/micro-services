package com.northbrain.search.repository;

import com.northbrain.search.model.Summary;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ISummaryRepository extends ElasticsearchRepository<Summary, String> {
    List<Summary> findByEntityNameContaining(String entityName);
}
