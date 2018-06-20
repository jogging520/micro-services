package com.northbrain.session.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 类名：令牌
 * 用途：返回JWT信息
 */
@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @NotNull
    private String  sessionId;          //会话编号
    @NotNull
    private String  userId;             //用户编号
    @NotNull
    private Long    lifeTime;           //寿命时长
    @NotNull
    private String  token;              //TOKEN
}
