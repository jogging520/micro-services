package com.northbrain.school.model;

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
public class School {
    @Id
    private String                  id;                 //id号
    @NotNull
    private String                  type;               //类型
    @NotNull
    private String                  category;           //类别（企业）
    @NotNull
    private String                  name;               //学校名称
    @NotNull
    private String                  region;             //归属行政区域编号
    private String                  masterName;         //校长姓名
    private String                  avatar;             //头像
    @NotNull
    private String                  phone;              //电话号码
    @NotNull
    private Date                    createTime;         //创建时间
    @NotNull
    private Date                    timestamp;          //状态时间
    @NotNull
    private String                  status;             //状态
    @NotNull
    private String                  serialNo;           //操作流水号
    private String                  description;        //描述
}
