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
    private String              id;                             //会话ID
    @NotNull
    private String              name;                           //姓名
    private String              otherName;                      //曾用名
    @NotNull
    private String              regionId;                       //归属的行政区域
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
    private String              schoolId;                       //所在学校
    private String              grade;                          //班级
    private String              period;                         //学段
    private String              idCardNo;                       //身份证号
    private String              schoolRollId;                   //学籍号
    private Float               distanceOfSchoolAndHome;        //学校到家的距离
    private String              trafficCondition;               //交通状况
    private String[]            aids;                           //近三年的教育资助
    private String[]            demands;                        //受教育的困难及需求
    private String[]            solutionIds;                    //帮扶对策
    private Boolean             isPoor;                         //是否贫困
    @NotNull
    private Date                createTime;                     //创建时间
    @NotNull
    private Date                timestamp;                      //状态时间
    @NotNull
    private String              status;                         //状态
    @NotNull
    private String              operationId;                    //操作流水号
    private String              description;                    //描述
}
