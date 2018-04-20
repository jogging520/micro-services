package com.northbrain.user.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class UserHistory {
    @Id
    private String      userHistoryId;      //用户历史编号
    private String      operationType;      //操作类型：增删改
    private String      userId;             //用户编号
    private String      type;               //用户类型
    private String      userName;           //用户名称
    private String      password;           //密码
    private String      mobile;             //手机号码
    private String[]    appTypes;           //可以登录的应用类型
    private String[]    roleIds;            //角色编号
    private String[]    organizationIds;    //组织编号
    private String[]    emails;             //电子邮件
    private String[]    wechates;           //微信号码
    private Date        createTime;         //创建时间
    private Date        timestamp;          //时间戳
    private String      status;             //状态
}
