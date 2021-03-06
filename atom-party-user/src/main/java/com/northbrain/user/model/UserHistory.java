package com.northbrain.user.model;

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
public class UserHistory {
    @Id
    private String                  id;                 //id编号
    @NotNull
    private String                  operationType;      //操作类型
    @NotNull
    private String                  userId;             //用户编号
    @NotNull
    private String                  type;               //类型
    @NotNull
    private String                  name;               //用户名称
    @NotNull
    private String                  password;           //密码
    @NotNull
    private String                  salt;               //加密盐
    @NotNull
    private String                  realName;           //真实姓名
    private String                  avatar;             //头像
    @NotNull
    private String[]                appTypes;           //可以登录的应用类型
    @NotNull
    private String                  category;           //类别（企业）
    @NotNull
    private String[]                roles;              //角色
    private Integer[]               permissions;        //权限
    @NotNull
    private Map<String, String[]>   affiliations;       //归属
    private String[]                mobiles;            //手机号码
    private String[]                emails;             //电子邮件
    private String[]                weChats;            //微信号码
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
