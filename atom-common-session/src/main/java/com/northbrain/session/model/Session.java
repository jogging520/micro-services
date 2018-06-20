package com.northbrain.session.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    private String          id;                 //id号
    @NotNull
    private String          type;               //类型
    @NotNull
    private String          appType;            //应用类型：WEB、APP、WECHAT、CMS
    @NotNull
    private String          userId;             //用户编号
    @NotNull
    private String          userName;           //用户名
    private String          mobile;             //手机号码
    @NotNull
    private Date            loginTime;          //登录时间
    @NotNull
    private Long            lifeTime;           //寿命，毫秒
    @NotNull
    private Date            createTime;         //创建时间
    @NotNull
    private Date            timestamp;          //状态时间
    @NotNull
    private String          status;             //状态
    @NotNull
    private String          description;        //描述
}
