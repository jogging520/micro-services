package com.northbrain.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private String      studentId;          //会话ID
    private String      name;
    private String      IDCardNo;
    private Integer     age;
    private String      sex;
    private String      schoolId;
    private String      schoolName;
    private String      province;
    private String      city;
    private String      county;
    private String      address;
    private String      phone;
    private String      level;
    private String      grade;
    private String      status;
    private Date        createDate;
    private Date        statusDate;
}
