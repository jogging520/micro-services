package com.northbrain.user.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String          id;                 //id号
    @NotNull
    private String          type;               //类型
    @NotNull
    private String          userName;           //用户名称
    @NotNull
    private String          password;           //密码
    @NotNull
    private String          realName;           //真实姓名
    @NotNull
    private String[]        appTypes;           //可以登录的应用类型
    @NotNull
    private String[]        roleIds;            //角色编号
    @NotNull
    private Affiliation[]   affiliations;       //归属
    private String[]        mobiles;            //手机号码
    private String[]        emails;             //电子邮件
    private String[]        wechates;           //微信号码
    @NotNull
    private Date            createTime;         //创建时间
    @NotNull
    private Date            timestamp;          //状态时间
    @NotNull
    private String          status;             //状态
    @NotNull
    private String          operationId;        //操作流水号
    @NotNull
    private String          description;        //描述
}
