package com.northbrain.session.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class SessionHistory {
    @Id
    private String  sessionHistoryId;   //ID
    private String  operationType;      //操作类型：增删改
    private String  sessionId;          //会话ID
    private String  appType;            //应用类型：WEB、APP、WECHAT、CMS
    private String  userName;           //用户名
    private String  mobile;             //手机号码
    private Date    createTime;         //创建时间
    private Date    loginTime;          //登录时间
    private Date    timestamp;          //时间戳
    private String  status;             //状态：已登录、已主动登出、已被动登出
    private Long    lifeTime;           //寿命，毫秒
}
