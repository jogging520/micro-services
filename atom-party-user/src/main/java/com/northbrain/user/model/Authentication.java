package com.northbrain.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Authentication {
    private String  channelType;        //渠道类型：WEB、APP、WECHAT、CMS
    private String  userId;             //用户编号
    private String  roleId;             //角色编号
    private String  organizationId;     //组织机构编号
    private Boolean verification;       //校验结果
}
