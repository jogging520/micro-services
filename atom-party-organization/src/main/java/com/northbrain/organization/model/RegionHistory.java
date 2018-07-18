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
public class RegionHistory {
    @Id
    private String              id;                             //id编号
    @NotNull
    private String              operationType;                  //操作类型
    @NotNull
    private String              regionId;                       //区域ID
    @NotNull
    private String              code;                           //编码
    @NotNull
    private String              name;                           //名称
    @NotNull
    private String              type;                           //类型
    @NotNull
    private String              category;                       //类别（企业）
    @NotNull
    private String              level;                          //级别
    @NotNull
    private Float               longitude;                      //中心经度
    @NotNull
    private Float               latitude;                       //中心纬度
    //private 地理位置信息 TODO
    @NotNull
    private Date                createTime;                     //创建时间
    @NotNull
    private Date                timestamp;                      //状态时间
    @NotNull
    private String              status;                         //状态
    @NotNull
    private String              serialNo;                       //操作流水号
    private String              description;                    //描述
    private Region[]            children;                       //下级区域
}
