package com.northbrain.organization.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * 类名：组织机构
 * 用途：是参与者中的组织机构，各类实体附属于组织机构。
 */
@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    @Id
    private String          id;             //组织机构ID
    @NotNull
    private String          code;           //组织机构编码（区别于ID，有可能与ID相同取值）
    @NotNull
    private String          name;           //组织机构名称
    @NotNull
    private String          regionId;       //组织机构归谁行政区域
    private String          parent;         //组织机构父组织
    private Organization[]  children;       //组织机构子组织
}
