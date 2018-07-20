package com.northbrain.search.repository;

import com.northbrain.search.model.Summary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ISummaryRepository extends ElasticsearchRepository<Summary, String> {
    List<Summary> findByCategoryAndNameContaining(String category, String name);

    Page<Summary> findByCategoryAndEntity(String category, String entity, Pageable pageable);


}
