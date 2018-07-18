package com.northbrain.organization.model;

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
public class OrganizationHistory {
    @Id
    private String              id;                             //id编号
    @NotNull
    private String              operationType;                  //操作类型
    @NotNull
    private String              organizationId;                 //组织机构ID
    @NotNull
    private String              code;                           //组织机构编码（区别于ID，有可能与ID相同取值）
    @NotNull
    private String              name;                           //组织机构名称
    @NotNull
    private String              type;                           //类型
    @NotNull
    private String              category;                       //类别（企业）
    @NotNull
    private String              region;                         //组织机构归谁行政区域
    private String              parent;                         //组织机构父组织
    @NotNull
    private Date                createTime;                     //创建时间
    @NotNull
    private Date                timestamp;                      //状态时间
    @NotNull
    private String              status;                         //状态
    @NotNull
    private String              serialNo;                       //操作流水号
    private String              description;                    //描述
    private Organization[]      children;                       //组织机构子组织
}
