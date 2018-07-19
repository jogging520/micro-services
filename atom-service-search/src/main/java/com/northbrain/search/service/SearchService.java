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

    /**
     * 方法：搜索
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param conditions 条件
     * @return 符合条件的摘要信息
     */
    public Flux<Summary> querySummariesByConditions(String serialNo,
                                                    String category,
                                                    Flux<Condition> conditions) {
        log.info(Constants.SEARCH_OPERATION_SERIAL_NO + serialNo);

        //TODO 判断category
        return conditions
                .flatMap(condition -> {
                    QueryStringQueryBuilder queryStringQueryBuilder =
                            new QueryStringQueryBuilder(condition.getFilters());

                    return Flux.fromIterable(this.summaryRepository
                            .search(queryStringQueryBuilder));
                });
    }

    /**
     * 方法：创建摘要信息
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param summaries 摘要
     * @return 创建成功的摘要信息
     */
    public Flux<Summary> createSummaries(String serialNo,
                                         String category,
                                         Flux<Summary> summaries) {
        //TODO 判断category
        return Flux.fromIterable(this.summaryRepository
                .saveAll(summaries.toIterable()))
                .map(summary -> {
                    log.info(Constants.SEARCH_OPERATION_SERIAL_NO + serialNo);
                    log.info(summary.toString());

                    return summary;
                });
    }

    public Flux<Void> deleteSummaries(String serialNo,
                                      String category,
                                      Flux<Summary> summaries) {
        log.info(Constants.SEARCH_OPERATION_SERIAL_NO + serialNo);

        //TODO 判断category
        this.summaryRepository
                .deleteAll(summaries.toIterable());

        return Flux.empty();
    }
}
