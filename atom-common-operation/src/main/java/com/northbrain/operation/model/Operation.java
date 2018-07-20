package com.northbrain.operation.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

/**
 * 类名：操作记录
 * 用途：记录各类操作的工单
 */
@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Operation {
    @Id
    private String                      id;                 //id号
    @NotNull
    private String                      type;               //类型
    @NotNull
    private String                      appType;            //应用程序类型
    @NotNull
    private String                      category;           //类别（企业）
    @NotNull
    private String                      user;               //用户编号
    @NotNull
    private String                      realName;           //操作者真实姓名
    @NotNull
    private String                      session;            //会话编号
    @NotNull
    private String                      businessType;       //业务类型
    @NotNull
    private Date                        createTime;         //创建时间
    @NotNull
    private Date                        timestamp;          //状态时间
    @NotNull
    private String                      status;             //状态
    private String                      description;        //描述
}
