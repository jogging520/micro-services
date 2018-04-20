package com.northbrain.session.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
    private String  sessionId;          //会话编号
    private Long    lifeTime;           //寿命时长
    private String  token;              //TOKEN
}
