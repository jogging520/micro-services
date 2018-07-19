package com.northbrain.session.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 类名：尝试类
 * 用途：用于控制尝试登录的次数限制
 */
@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Attempt {
    @Id
    private String                  id;                 //id编号
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
