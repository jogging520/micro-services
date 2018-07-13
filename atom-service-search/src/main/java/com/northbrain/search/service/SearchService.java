package com.northbrain.search.service;

import com.northbrain.search.model.Condition;
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

    public Flux<Summary> querySummaries(String serialNo,
                                        Flux<Condition> conditions) {
        log.info(Constants.SEARCH_OPERATION_SERIAL_NO + serialNo);

        return conditions
                .flatMap(condition -> {
                    QueryStringQueryBuilder queryStringQueryBuilder =
                            new QueryStringQueryBuilder(condition.getFilters());

                    return Flux.fromIterable(this.summaryRepository
                            .search(queryStringQueryBuilder));
                });
    }

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

    public Flux<Void> deleteSummaries(String serialNo,
                                      Flux<Summary> summaries) {
        log.info(Constants.SEARCH_OPERATION_SERIAL_NO + serialNo);

        this.summaryRepository
                .deleteAll(summaries.toIterable());

        return Flux.empty();
    }
}
