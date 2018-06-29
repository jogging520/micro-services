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
@Document(indexName = "summary", shards = 3, replicas = 3, refreshInterval = "-1")
public class Summary {
    @Id
    private String      id;                 //id
    @Field
    private String      entityId;           //实体的真实ID
    @Field
    private String      entityName;         //实体的名称
    @Field
    private String      entityDescription;  //实体的描述
    @Field
    private Date        createTime;         //创建时间
    @Field
    private Date        timestamp;          //时间戳
    @Field
    private String      status;             //状态

}
