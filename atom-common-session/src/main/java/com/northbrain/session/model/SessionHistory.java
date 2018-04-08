package com.northbrain.session.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Accessors(fluent=true, chain=true)
@Builder
@Document
@NoArgsConstructor
public class SessionHistory {
    @Id
    private String  id;                 //ID
    private String  sessionId;          //会话ID
    private String  channelType;        //渠道类型：WEB、APP、WECHAT、CMS
    private String  userId;             //用户编号
    private String  roleId;             //角色编号
    private String  organizationId;     //组织编号
    private Date    createTime;         //创建时间
    private Date    loginTime;          //登录时间
    private Date    timestamp;          //时间戳
    private String  status;             //状态：已登录、已主动登出、已被动登出
    private Long    lifeTime;           //寿命，毫秒
}
