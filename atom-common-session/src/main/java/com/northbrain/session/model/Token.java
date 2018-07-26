package com.northbrain.session.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 类名：令牌
 * 用途：返回JWT信息
 */
@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Token {
    @NotNull
    private String  session;            //会话编号
    @NotNull
    private String  user;               //用户编号
    @NotNull
    private Long    lifeTime;           //寿命时长
    @NotNull
    private String  jwt;                //jwt
    @NotNull
    private String  downPublicKey;      //下行加密密钥（公钥）
    @NotNull
    private String  upPrivateKey;       //上行解密密钥（私钥）
    @NotNull
    private String  status;             //状态
}
