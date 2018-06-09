package com.northbrain.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 类名：用户归属组织机构类
 * 用途：定义用户归属和拥有的组织机构。
 */
@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Affiliation {
    @Id
    private String      affiliationId;      //归属编码
    private String      userId;             //用户编码
    private String      type;               //类型
    private String      organizationId;     //机构编码（用户拥有的可查看组织机构列表，这个不为空时，取该机构列表，否则取归属组织编码）
    private Date        createTime;         //创建时间
    private Date        timestamp;          //时间戳
    private String      status;             //状态
}
