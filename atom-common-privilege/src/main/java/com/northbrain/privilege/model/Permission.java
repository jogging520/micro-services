package com.northbrain.privilege.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 类名：权限
 * 编码格式：XXXXXXXX，数值型
 *      第一位：   CMS--1;WEB--2;APP--3;WECHAT:--4
 *      第二、三位：归属域
 *      第四位：   类型
 *      第五至八位：权限编号
 */
@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Permission {
    @Id
    private Integer                 id;                 //id编号
    @NotNull
    private String                  type;               //类型，增删改查
    @NotNull
    private String                  name;               //名称
    @NotNull
    private String                  domain;             //归属域，如菜单、列表、按钮等
    @NotNull
    private String                  appType;            //应用类型
    @NotNull
    private String                  category;           //类别（企业）
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
