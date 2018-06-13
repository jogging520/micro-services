package com.northbrain.user.model;

import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String          userId;             //用户编号
    @NotNull
    private String          type;               //用户类型
    @NotNull
    private String          userName;           //用户名称
    @NotNull
    private String          password;           //密码
    private String[]        appTypes;           //可以登录的应用类型
    private String[]        roleIds;            //角色编号
    private Affiliation[]   affiliations;       //归属
    private String[]        mobiles;            //手机号码
    private String[]        emails;             //电子邮件
    private String[]        wechates;           //微信号码
    private Date            createTime;         //创建时间
    private Date            timestamp;          //时间戳
    private String          status;             //状态
    private String          description;        //描述
}
