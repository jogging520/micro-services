package com.northbrain.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 类名：鉴权结果类
 * 用途：用于返回校验结果
 */
@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authentication {
    private String      userId;             //用户编号
    private String      userName;           //用户名称
    private Boolean     result;             //校验结果
}
