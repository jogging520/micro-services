package com.northbrain.search.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class SearchHistory {
    @Id
    private String                  id;                 //id编号
    @NotNull
    private String                  operationType;      //操作类型
    @NotNull
    private String                  searchId;           //搜索id编号
    @NotNull
    private String                  type;               //类型
    @NotNull
    private String[]                appTypes;           //可以登录的应用类型
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
