package com.northbrain.search.service;

import java.util.ArrayList;
import java.util.List;

import com.northbrain.search.model.Constants;
import com.northbrain.search.model.Summary;
import com.northbrain.search.repository.ISummaryRepository;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import reactor.core.publisher.Flux;

@Service
@Log
public class SearchService {
    private final ISummaryRepository summaryRepository;

    public SearchService(ISummaryRepository summaryRepository) {
        this.summaryRepository = summaryRepository;
    }

    public Summary querySummaries(String condition) {
        return this.summaryRepository
                .searchSimilar(new QueryStringQueryBuilder(condition), );
    }

    //是否转换成Flux
    public Flux<Summary> createSummaries(String serialNo,
                                         Flux<Summary> summaries) {
        return Flux.fromIterable(this.summaryRepository
                .saveAll(summaries.toIterable()))
                .map(summary -> {
                    log.info(Constants.SEARCH_OPERATION_SERIAL_NO + serialNo);
                    log.info(summary.toString());

                    return summary;
                });
    }
}
