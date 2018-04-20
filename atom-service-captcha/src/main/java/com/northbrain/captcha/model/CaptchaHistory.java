package com.northbrain.captcha.model;

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
public class CaptchaHistory {
    @Id
    private String      captchaHistoryId;   //用户历史编号
    private String      operationType;      //操作类型：增删改
    private String      captchaId;          //验证码ID
    private String      mobile;             //手机号码
    private String      code;               //验证码
    private Date        createTime;         //创建时间
    private Date        timestamp;          //时间戳
    private String      status;             //状态
}
