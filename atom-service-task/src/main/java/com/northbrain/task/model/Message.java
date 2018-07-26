package com.northbrain.task.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 类名：消息类
 * 用途：用于在消息中间件传递消息和数据库存储。
 */
@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Message {
    @Id
    private String                  id;                 //id编号
    @NotNull
    private String                  type;               //类型
    @NotNull
    private String                  category;           //类别（企业）
    @NotNull
    private String[]                appTypes;           //接收的app类型
    @NotNull
    private String                  from;               //发送者
    @NotNull
    private String[]                to;                 //接收者（角色或者用户）
    @NotNull
    private String                  content;            //内容
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
