package com.northbrain.operation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 类名：操作记录
 * 用途：记录各类操作的工单
 */
@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    @Id
    private String          id;                 //id号
    @NotNull
    private String          type;               //类型
    @NotNull
    private String          appType;            //应用程序类型
    @NotNull
    private String          userId;             //用户编号
    @NotNull
    private String          sessionId;          //会话编号
    @NotNull
    private String          organizationId;     //组织机构编号
    @NotNull
    private String          businessType;       //业务类型
    @NotNull
    private Date            createTime;         //创建时间
    @NotNull
    private Date            timestamp;          //状态时间
    @NotNull
    private String          status;             //状态
    private String          description;        //描述
}
