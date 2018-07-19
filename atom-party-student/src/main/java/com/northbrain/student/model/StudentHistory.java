package com.northbrain.student.model;

import javax.validation.constraints.NotNull;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class StudentHistory {
    @Id
    private String              id;                             //id编号
    @NotNull
    private String              operationType;                  //操作类型
    @NotNull
    private String              studentId;                      //用户编号
    @NotNull
    private String              type;                           //类型
    @NotNull
    private String              category;                       //类别（企业）
    @NotNull
    private String              name;                           //姓名
    private String              otherName;                      //曾用名
    @NotNull
    private String              region;                         //归属的行政区域
    private String              avatar;                         //头像
    @NotNull
    private String              gender;                         //性别
    @NotNull
    private Date                birthday;                       //出生年月，如2006.08
    @NotNull
    private String              nationality;                    //民族
    @NotNull
    private String              politics;                       //政治面貌
    @NotNull
    private Boolean             isAtSchool;                     //是否上学
    private String              health;                         //监控状况
    private String              family;                         //家庭
    private String              school;                         //所在学校
    private String              grade;                          //班级
    private String              period;                         //学段
    private String              idCardNo;                       //身份证号
    private String              schoolRollNo;                   //学籍号
    private Float               distanceOfSchoolAndHome;        //学校到家的距离
    private String              trafficCondition;               //交通状况
    private String[]            aids;                           //近三年的教育资助
    private String[]            demands;                        //受教育的困难及需求
    private String[]            solutions;                      //帮扶对策
    private Boolean             isPoor;                         //是否贫困
    @NotNull
    private Date                createTime;                     //创建时间
    @NotNull
    private Date                timestamp;                      //状态时间
    @NotNull
    private String              status;                         //状态
    @NotNull
    private String              serialNo;                       //操作流水号
    private String              description;                    //描述
}
