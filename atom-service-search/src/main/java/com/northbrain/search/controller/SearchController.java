package com.northbrain.search.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.northbrain.search.model.Constants;
import com.northbrain.search.model.Summary;
import com.northbrain.search.service.SearchService;

import reactor.core.publisher.Flux;

@RestController
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * 方法：创建摘要信息
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param summaries 摘要
     * @return 创建成功的摘要信息
     */
    @PostMapping(Constants.SEARCH_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Summary>> createSummaries(@RequestParam String serialNo,
                                                         @RequestParam String category,
                                                         @RequestBody Flux<Summary> summaries) {
        return ResponseEntity.ok()
                .body(this.searchService
                        .createSummaries(serialNo, category, summaries));
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
    @GetMapping(Constants.SEARCH_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Summary>> querySummariesByCondition(@RequestParam String serialNo,
                                                                   @RequestParam String appType,
                                                                   @RequestParam String category,
                                                                   @RequestParam String user,
                                                                   @RequestParam String condition) {
        return ResponseEntity.ok()
                .body(this.searchService
                        .querySummariesByCondition(serialNo, appType, category, user, condition));

    }
}
