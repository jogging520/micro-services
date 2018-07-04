package com.northbrain.operation.model;

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
public class Record {
    @Id
    private String                      id;                 //id号
    @NotNull
    private String                      type;               //类型
    @NotNull
    private String                      businessType;       //业务类型
    @NotNull
    private String                      url;                //url
    @NotNull
    private Date                        createTime;         //创建时间
    @NotNull
    private Date                        timestamp;          //状态时间
    @NotNull
    private String                      status;             //状态
    @NotNull
    private String                      serialNo;           //操作流水号
    private String                      description;        //描述
}
