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
public class User {
    @Id
    private String      userId;             //用户编号
    private String      type;               //用户类型
    private String      userName;           //用户名称
    private String      alias;              //别名
    private String      password;           //密码
    private String      email;              //电子邮件
    private String[]    phone;              //电话号码
    private String      roleId;             //角色编号
    private String      organizationId;     //组织编号
    private Date        createTime;         //创建时间
    private Date        timestamp;          //时间戳
    private String      status;             //状态
}
