package com.northbrain.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private String              id;          //会话ID
    private String              name;
    private String              idCardNo;
    private Integer             age;
    private String              sex;
    private String              schoolId;
    private String              schoolName;
    private String              province;
    private String              city;
    private String              county;
    private String              address;
    private String              phone;
    private String              level;
    private String              grade;
    @NotNull
    private Date                createTime;         //创建时间
    @NotNull
    private Date                timestamp;          //状态时间
    @NotNull
    private String              status;             //状态
    @NotNull
    private String              operationId;        //操作流水号
    private String              description;        //描述
}
