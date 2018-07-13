package com.northbrain.search.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;

@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = Constants.SEARCH_SUMMARY_INDEX_NAME,
          shards = 3,
          replicas = 3,
          refreshInterval = Constants.SEARCH_SUMMARY_REFRESH_INTERVAL)
public class Summary {
    @Id
    private String      id;                 //id
    @Field
    private String      entity;             //实体的真实ID
    @Field
    private String      type;               //实体的类型
    @Field
    private String      name;               //实体的名称
    @Field
    private String      description;        //实体的描述
    @Field
    private Date        createTime;         //创建时间
    @Field
    private Date        timestamp;          //时间戳
    @Field
    private String      status;             //状态

}
