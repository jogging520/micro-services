package com.northbrain.util.security;

import com.northbrain.util.model.Constants;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * 类名：密码类
 * 用途：用于密码的加密和验证。
 */
@Component
@Log
public class Password {
    /**
     * 方法：加密
     * @param password 待加密的密码明文
     * @param salt 盐
     * @return 密文
     */
    public static String encrypt(String password, String salt) {
        String encryptedPassword = "";

        try {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(),
                    fromHex(salt), Constants.UTIL_SECURITY_PBKDF2_ITERATIONS,
                    Constants.UTIL_SECURITY_PBKDF2_HASH_BIT_SIZE);

            SecretKeyFactory secretKeyFactory =
                    SecretKeyFactory.getInstance(Constants.UTIL_SECURITY_PBKDF2_ALGORITHM);

            encryptedPassword = toHex(secretKeyFactory.generateSecret(pbeKeySpec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return encryptedPassword;
    }

    /**
     * 方法：密码验证
     * @param password 待验证密码明文
     * @param passwordToBeVerified 用户密码密文
     * @param salt 用户盐
     * @return 验证结果
     */
    public static Boolean verify(String password, String passwordToBeVerified, String salt) {
        String encryptedPassword = encrypt(password, salt);

        return encryptedPassword.equals(passwordToBeVerified);
    }

    public static String generateSalt() {
        byte[] salt = new byte[Constants.UTIL_SECURITY_PBKDF2_SALT_BYTE_SIZE];

        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.nextBytes(salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return toHex(salt);
    }

    /**
     * 方法：十六进制字符串转换成二进制字符串
     * @param array 十六进制字符串
     * @return 二进制字符串
     */
    private static String toHex(byte[] array) {
        BigInteger bigInteger = new BigInteger(1, array);
        String hex = bigInteger.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }

    /**
     * 方法：二进制字符串转换成十六进制字符串
     * @param hex 二进制字符串
     * @return 十六进制字符串
     */
    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

}
