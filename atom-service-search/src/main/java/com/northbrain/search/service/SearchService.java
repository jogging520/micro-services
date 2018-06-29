package com.northbrain.search.service;

import com.northbrain.search.model.Summary;
import com.northbrain.search.repository.ISummaryRepository;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private final ISummaryRepository summaryRepository;

    public SearchService(ISummaryRepository summaryRepository) {
        this.summaryRepository = summaryRepository;
    }

    public Summary querySummaries(String condition) {
        return this.summaryRepository
                .searchSimilar(new QueryStringQueryBuilder(condition), );
    }
}
