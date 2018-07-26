package com.northbrain.search.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 类名：搜索摘要信息类
 * 用途：用于在ES存储各类产品、用户、资源的摘要信息
 * analyzer：建立索引时指定的分词器；searchAnalyzer：搜索时使用的分词器
 */
@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = Constants.SEARCH_SUMMARY_INDEX_NAME,
          type = Constants.SEARCH_SUMMARY_TYPE_NAME,
          shards = 3,
          replicas = 3,
          refreshInterval = Constants.SEARCH_SUMMARY_REFRESH_INTERVAL)
public class Summary {
    @Id
    private String      id;                 //id
    @Field
    private String      type;               //实体的类型
    @Field
    private String      category;           //类别（企业）
    @Field(type = FieldType.Text, searchAnalyzer = Constants.SEARCH_SUMMARY_ANALYZER_PINYIN, analyzer = Constants.SEARCH_SUMMARY_ANALYZER_PINYIN)
    private String      name;               //实体的名称
    @Field(type = FieldType.Text, searchAnalyzer = Constants.SEARCH_SUMMARY_ANALYZER_PINYIN, analyzer = Constants.SEARCH_SUMMARY_ANALYZER_PINYIN)
    private String      description;        //实体的描述
    @Field
    private String      status;             //状态
    @Field
    private String      serialNo;           //操作流水号

}
