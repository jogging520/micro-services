package com.northbrain.user.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 类名：用户类
 * 用途：管理各类系统的使用用户。
 */
@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class User {
    @Id
    private String                  id;                 //id编号
    @NotNull
    private String                  type;               //类型
    @NotNull
    private String                  userName;           //用户名称
    @NotNull
    private String                  password;           //密码
    @NotNull
    private String                  realName;           //真实姓名
    private String                  avatar;             //头像
    @NotNull
    private String[]                appTypes;           //可以登录的应用类型
    @NotNull
    private String[]                roles;              //角色
    private Integer[]               permissions;        //权限
    @NotNull
    private Map<String, String[]>   affiliations;       //归属
    private String[]                mobiles;            //手机号码
    private String[]                emails;             //电子邮件
    private String[]                wechates;           //微信号码
    @NotNull
    private String                  salt;               //加密盐
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
