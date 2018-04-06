package com.northbrain.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类名：JWT工具类
 * 用途：JWT是 Json Web Token 的缩写。它是基于 RFC 7519 标准定义的一种可以安全传输的 小巧 和 自包含 的JSON对象。
 * 由于数据是使用数字签名的，所以是可信任的和安全的。
 * JWT可以使用HMAC算法对secret进行加密或者使用RSA的公钥私钥对来进行签名。
 * @author Jiakun
 * @version 1.0
 *
 */
public class JsonWebTokenUtil {
    private final Logger logger = LoggerFactory.getLogger(JsonWebTokenUtil.class);

    public static String generateJsonWebToken(int loginId, int partyId, int roleId, int organizationId)
            throws Exception {
        return null;
    }
}
