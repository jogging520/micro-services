package com.northbrain.session.util;

import com.northbrain.session.model.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类名：JWT工具类
 * 用途：JWT是 Json Web Token 的缩写。它是基于 RFC 7519 标准定义的一种可以安全传输的 小巧 和 自包含 的JSON对象。
 * 由于数据是使用数字签名的，所以是可信任的和安全的。
 * JWT可以使用HMAC算法对secret进行加密或者使用RSA的公钥私钥对来进行签名。
 *
 */
@Log
public class JsonWebTokenUtil {
    public static String generateJsonWebToken(String session, String appType, String key,
                                              String company, String audience, String issuer, Long lifeTime)
            throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);

        //生成签名密钥

        byte[] keySecretBytes = key.getBytes();
        Key secretKey = new SecretKeySpec(keySecretBytes, signatureAlgorithm.getJcaName());

        //私有claims部分，目前只保持id号
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.SESSION_JWT_CLAIMS_SESSION, session);
        claims.put(Constants.SESSION_JWT_CLAIMS_APP_TYPE, appType);

        //添加构成JWT的参数
        JwtBuilder jwtBuilder = Jwts
                .builder()
                .setHeaderParam(Constants.SESSION_JWT_HEADER_PARAM_TYPE_NAME, Constants.SESSION_JWT_HEADER_PARAM_TYPE_VALUE)
                .setIssuedAt(now)
                .setClaims(claims)
                .setIssuer(issuer)
                .setAudience(audience)
                .setId(company)
                .signWith(signatureAlgorithm, secretKey);

        //添加Token过期时间
        long expireMillis = currentTimeMillis + lifeTime;
        Date expire = new Date(expireMillis);

        jwtBuilder.setExpiration(expire)
                .setNotBefore(now);

        //生成JWT
        return jwtBuilder.compact();
    }

    public static Map<String, String> parseJsonWebToken(String jsonWebToken, String key, String company,
                                                        String audience, String issuer)
            throws Exception {
        Claims claims = Jwts.parser()
                .setSigningKey(key.getBytes(Constants.SESSION_JWT_TOKEN_KEY_CHARSET))
                .parseClaimsJws(jsonWebToken.replace(Constants.SESSION_JWT_TOKEN_PREFIX, ""))
                .getBody();

        //判断发行者、接收者等信息是否正确
        if(!claims.getIssuer().equals(issuer) ||
                !claims.getId().equals(company) ||
                !claims.getAudience().equals(audience)) {
            log.info(Constants.SESSION_HINT_JWT_VERIFY_PUBLIC_CLAIM_FAILURE);
            throw new RuntimeException(Constants.SESSION_HINT_JWT_VERIFY_PUBLIC_CLAIM_FAILURE);
        }

        if(claims.getExpiration().before(new Date())) {
            log.info(Constants.SESSION_HINT_JWT_VERIFY_EXPIRATION_FAILURE);
            throw new RuntimeException(Constants.SESSION_HINT_JWT_VERIFY_EXPIRATION_FAILURE);
        }

        //解析私有claims
        Map<String, String> privateClaims = new HashMap<>();
        privateClaims.put(Constants.SESSION_JWT_CLAIMS_SESSION, (String) claims.get(Constants.SESSION_JWT_CLAIMS_SESSION));
        privateClaims.put(Constants.SESSION_JWT_CLAIMS_APP_TYPE, (String) claims.get(Constants.SESSION_JWT_CLAIMS_APP_TYPE));

        return privateClaims;
    }
}

