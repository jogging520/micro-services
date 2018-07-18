package com.northbrain.strategy.model;

import java.util.Date;
import java.util.Map;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 类名：策略类
 * 用途：用于存放各类参数，通过type区分大类，在parameters存储细粒度参数。
 */
@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Strategy {
    @Id
    private String                  id;                 //策略编号
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
