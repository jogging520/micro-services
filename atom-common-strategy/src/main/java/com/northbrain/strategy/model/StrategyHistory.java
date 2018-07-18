package com.northbrain.strategy.model;

import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class StrategyHistory {
    @Id
    private String                  id;                 //id编号
    @NotNull
    private String                  operationType;      //操作类型
    @NotNull
    private String                  strategyId;         //策略编号
    @NotNull
    private String                  type;               //类型
    @NotNull
    private String                  name;               //策略名称
    @NotNull
    private String                  appType;            //应用程序类型
    @NotNull
    private String                  category;           //类别（企业）
    private Map<String, String>     parameters;         //参数
    @NotNull
    private Date                    createTime;         //创建时间
    @NotNull
    private Date                    timestamp;          //状态时间
    @NotNull
    private String                  status;             //状态
    @NotNull
    private String                  serialNo;           //操作流水号
    private String                  description;        //描述
}
