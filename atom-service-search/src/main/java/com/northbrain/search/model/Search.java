package com.northbrain.search.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 类名：搜索类
 * 用途：用了存储各类搜索结果。
 */
@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Search {
    @Id
    private String                  id;                 //id编号
    @NotNull
    private String                  type;               //类型
    @NotNull
    private String                  appType;           //可以登录的应用类型
    @NotNull
    private String                  category;           //类别（企业）
    @NotNull
    private String                  user;               //用户编号
    @NotNull
    private String                  condition;          //搜索条件
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
