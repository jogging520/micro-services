package com.northbrain.captcha.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 类名：验证码
 * 用途：用于验证码的发送、校验等。
 */
@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Captcha {
    @Id
    private String      captchaId;          //验证码ID
    private String      mobile;             //手机号码
    private String      captcha;            //验证码
    private Date        createTime;         //创建时间
    private Date        timestamp;          //时间戳
    private String      status;             //状态
}
