package com.northbrain.strategy.model;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class StrategyHistory {
    @Id
    private String              strategyHistoryId;      //用户历史编号
    private String              operationType;          //操作类型：增删改
    private String              strategyId;             //策略编号
    private String              type;                   //类型
    private String              strategyName;           //策略名称
    private Map<String, String> strategy;               //策略
    private Date                createTime;             //创建时间
    private Date                timestamp;              //时间戳
    private String              status;                 //状态
}
