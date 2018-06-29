package com.northbrain.search.util;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

public class SearchUtil {
    public SearchQuery getSearchQuery(Integer pageNumber,
                                      Integer pageSize,
                                      String searchContent) {
        FunctionScoreQueryBuilder functionScoreQueryBuilder =
                QueryBuilders.
                .add


    }
}
