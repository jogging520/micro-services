package com.northbrain.session.model;

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
public class AttemptHistory {
    @Id
    private String                  id;                 //id编号
    @NotNull
    private String                  operationType;      //操作类型
    @NotNull
    private String                  attemptId;          //尝试登录id编号
    @NotNull
    private String                  type;               //类型
    @NotNull
    private String                  appType;            //应用类型
    @NotNull
    private String                  category;           //类别（企业）
    private String                  userName;           //尝试登录用户名
    private String                  password;           //尝试登录密码
    private String                  mobile;             //尝试登录手机号码
    @NotNull
    private Date                    attemptTime;        //尝试登录时间
    @NotNull
    private Date                    timestamp;          //状态时间
    @NotNull
    private String                  status;             //状态
    @NotNull
    private String                  serialNo;           //操作流水号
    private String                  description;        //描述
}
