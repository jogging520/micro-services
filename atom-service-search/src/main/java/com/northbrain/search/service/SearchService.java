package com.northbrain.search.service;

import com.northbrain.search.model.Constants;
import com.northbrain.search.model.Search;
import com.northbrain.search.model.Summary;
import com.northbrain.search.repository.ISearchRepository;
import com.northbrain.search.repository.ISummaryRepository;
import com.northbrain.util.timer.Clock;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log
public class SearchService {
    private final ISummaryRepository summaryRepository;
    private final ISearchRepository searchRepository;

    public SearchService(ISummaryRepository summaryRepository,
                         ISearchRepository searchRepository) {
        this.summaryRepository = summaryRepository;
        this.searchRepository = searchRepository;
    }

    /**
     * 方法：搜索
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param user 用户编号
     * @param condition 条件
     * @return 符合条件的摘要信息
     */
    public Flux<Summary> querySummariesByCondition(String serialNo,
                                                   String appType,
                                                   String category,
                                                   String user,
                                                   String condition) {
        log.info(Constants.SEARCH_OPERATION_SERIAL_NO + serialNo);
        log.info(Constants.SEARCH_OPERATION_QUERY_CONDITION + condition);

        this.searchRepository
                .save(Search.builder()
                        .type(Constants.SEARCH_TYPE_COMMON)
                        .appType(appType)
                        .category(category)
                        .user(user)
                        .condition(condition)
                        .createTime(Clock.currentTime())
                        .timestamp(Clock.currentTime())
                        .status(Constants.SEARCH_STATUS_ACTIVE)
                        .serialNo(serialNo)
                        .description(null)
                        .build())
                .subscribe();

        QueryStringQueryBuilder queryStringQueryBuilder =
                new QueryStringQueryBuilder(condition);

        return Flux.fromIterable(this.summaryRepository
                .search(queryStringQueryBuilder))
                .filter(summary -> summary.getCategory().equalsIgnoreCase(category));
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
        return summaries
                .filter(summary -> summary.getCategory().equalsIgnoreCase(category))
                .map(summary -> {
                    log.info(Constants.SEARCH_OPERATION_SERIAL_NO + serialNo);
                    log.info(summary.toString());

                    if(!this.summaryRepository
                            .existsById(summary.getId())) {
                        return this.summaryRepository
                                .save(summary
                                        .setCategory(category)
                                        .setSerialNo(serialNo)
                                        .setStatus(Constants.SEARCH_ERRORCODE_SUCCESS));
                    }

                    return Summary.builder()
                            .category(category)
                            .serialNo(serialNo)
                            .status(Constants.SEARCH_ERRORCODE_HAS_EXISTS)
                            .build();
                });
    }

    /**
     * 方法：更新摘要信息
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param summaries 摘要
     * @return 更新成功的摘要信息
     */
    public Flux<Summary> updateSummaries(String serialNo,
                                         String category,
                                         Flux<Summary> summaries) {
        return summaries
                .filter(summary -> summary.getCategory().equalsIgnoreCase(category))
                .map(summary -> {
                    log.info(Constants.SEARCH_OPERATION_SERIAL_NO + serialNo);
                    log.info(summary.toString());

                    if(this.summaryRepository
                            .existsById(summary.getId())) {
                        return this.summaryRepository
                                .save(summary
                                        .setCategory(category)
                                        .setSerialNo(serialNo)
                                        .setStatus(Constants.SEARCH_ERRORCODE_SUCCESS));
                    }

                    return Summary.builder()
                            .category(category)
                            .serialNo(serialNo)
                            .status(Constants.SEARCH_ERRORCODE_NOT_EXISTS)
                            .build();
                });
    }

    /**
     * 方法：删除摘要信息
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param summaries 摘要
     * @return 无
     */
    public Mono<Void> deleteSummaries(String serialNo,
                                      String category,
                                      Flux<Summary> summaries) {

        summaries
                .filter(summary -> summary.getCategory().equalsIgnoreCase(category))
                .subscribe(summary -> {
                    log.info(Constants.SEARCH_OPERATION_SERIAL_NO + serialNo);
                    log.info(summary.toString());

                    this.summaryRepository
                            .delete(summary);
                });

        return Mono.empty().then();
    }
}
