package com.northbrain.util.security;

import com.northbrain.util.model.Constants;
import com.northbrain.util.model.SecurityProperty;
import com.northbrain.util.tracer.StackTracer;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@Log
public class Crypt {
    private final SecurityProperty securityProperty;

    public Crypt(SecurityProperty securityProperty) {
        this.securityProperty = securityProperty;
    }

    /**
     * 方法：获取非对称加密的密钥对
     */
    public static void generateRSAKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator =
                    KeyPairGenerator.getInstance(Constants.UTIL_SECURITY_ASYMMETRIC_ALGORITHM);
            keyPairGenerator.initialize(Constants.UTIL_SECURITY_ASYMMETRIC_KEY_LENGTH);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            Key publicKey = keyPair.getPublic();
            String base64PublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());

            Key privateKey = keyPair.getPrivate();
            String base64PrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            log.info(Constants.UTIL_SECURITY_ASYMMETRIC_PUBLIC_KEY + base64PublicKey);
            log.info(Constants.UTIL_SECURITY_ASYMMETRIC_PRIVATE_KEY + base64PrivateKey);

        } catch (NoSuchAlgorithmException e) {
            StackTracer.printException(e);
        }
    }

    /**
     * 方法：获取公钥
     * @param base64PublicKey BASE64编码后的公钥字符串
     * @return 公钥
     */
    private PublicKey getPublicKey(String base64PublicKey) {

        PublicKey publicKey = null;

        try {
            X509EncodedKeySpec keySpec =
                    new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance(Constants.UTIL_SECURITY_ASYMMETRIC_ALGORITHM);
            publicKey = keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            StackTracer.printException(e);
        }

        return publicKey;
    }

    /**
     * 方法：获取私钥
     * @param base64PrivateKey BASE64编码后的私钥字符串
     * @return 私钥
     */
    private PrivateKey getPrivateKey(String base64PrivateKey) {
        PrivateKey privateKey = null;

        try {
            PKCS8EncodedKeySpec keySpec =
                    new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance(Constants.UTIL_SECURITY_ASYMMETRIC_ALGORITHM);
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            StackTracer.printException(e);
        }
        return privateKey;
    }

    /**
     * 方法：加密（非对称）
     * @param data 明文
     * @param base64PublicKey BASE64编码后的公钥字符串
     * @return 密文（BASE64编码）
     */
    private String encrypt(byte[] data, String base64PublicKey) {
        String encryptedData = null;

        try {
            Cipher cipher = Cipher.getInstance(Constants.UTIL_SECURITY_ASYMMETRIC_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(base64PublicKey));

            encryptedData = Base64.getEncoder().encodeToString(cipher.doFinal(data));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
            StackTracer.printException(e);
        }

        return encryptedData;
    }

    /**
     * 方法：解密
     * @param data 密文（BASE64编码）
     * @param base64PrivateKey BASE64编码后的私钥字符串
     * @return 明文
     */
    private String decrypt(byte[] data, String base64PrivateKey) {
        String decryptedData = "";

        try {
            Cipher cipher = Cipher.getInstance(Constants.UTIL_SECURITY_ASYMMETRIC_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(base64PrivateKey));

            decryptedData = new String(cipher.doFinal(Base64.getDecoder().decode(data)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            StackTracer.printException(e);
        }

        return decryptedData;
    }

    /**
     * 方法：签名
     * @param data 明文
     * @param base64PrivateKey BASE64编码后的私钥字符串
     * @return 签名（BASE64编码）
     */
    private String sign(String data, String base64PrivateKey) {
        String signedData = "";

        try {
            Signature signature = Signature.getInstance(Constants.UTIL_SECURITY_SIGNATURE_ALGORITHM);
            signature.initSign(getPrivateKey(base64PrivateKey));
            signature.update(data.getBytes());

            signedData = Base64.getEncoder().encodeToString(signature.sign());
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            StackTracer.printException(e);
        }

        return signedData;
    }

    /**
     * 方法：验签
     * @param data 密文
     * @param signedData 签名（BASE64编码）
     * @param base64PublicKey BASE64编码后的公钥字符串
     * @return 验证结果
     */
    private Boolean verify(String data, String signedData, String base64PublicKey) {
        Boolean verifiedResult = false;

        try {
            Signature signature = Signature.getInstance(Constants.UTIL_SECURITY_SIGNATURE_ALGORITHM);
            signature.initVerify(getPublicKey(base64PublicKey));
            signature.update(data.getBytes());

            verifiedResult = signature.verify(Base64.getDecoder().decode(signedData.getBytes()));
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            StackTracer.printException(e);
        }

        return verifiedResult;
    }

    /**
     * 方法：用户上行加密
     * @param data 明文
     * @param appType 应用类型
     * @return 密文
     */
    public String encrypt4UserUpStream(String data, String appType) {
        String base64PublicKey;

        switch (appType) {
            case Constants.UTIL_SECURITY_APP_TYPE_APP:
                base64PublicKey = securityProperty.getAppUpPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_WEB:
                base64PublicKey = securityProperty.getWebUpPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_CMS:
                base64PublicKey = securityProperty.getCmsUpPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_LED:
                base64PublicKey = securityProperty.getLedUpPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_MON:
                base64PublicKey = securityProperty.getMonUpPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_WCT:
                base64PublicKey = securityProperty.getWctUpPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_WCA:
                base64PublicKey = securityProperty.getWcaUpPublicKey();
                break;
            default:
                base64PublicKey = "";
                break;
        }

        return encrypt(Base64.getEncoder().encode(data.getBytes()),
                base64PublicKey);
    }

    /**
     * 方法：用户下行解密
     * @param data 密文
     * @param appType 应用类型
     * @param isTemporary 是否为临时
     * @return 明文
     */
    public String decrypt4UserDownStream(String data, String appType, Boolean isTemporary) {
        String base64PrivateKey;

        switch (appType) {
            case Constants.UTIL_SECURITY_APP_TYPE_APP:
                if(isTemporary)
                    base64PrivateKey = securityProperty.getAppTemporaryPrivateKey();
                else
                    base64PrivateKey = securityProperty.getAppDownPrivateKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_WEB:
                if(isTemporary)
                    base64PrivateKey = securityProperty.getWebTemporaryPrivateKey();
                else
                    base64PrivateKey = securityProperty.getWebDownPrivateKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_CMS:
                if(isTemporary)
                    base64PrivateKey = securityProperty.getCmsTemporaryPrivateKey();
                else
                    base64PrivateKey = securityProperty.getCmsDownPrivateKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_LED:
                if(isTemporary)
                    base64PrivateKey = securityProperty.getLedTemporaryPrivateKey();
                else
                    base64PrivateKey = securityProperty.getLedDownPrivateKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_MON:
                if(isTemporary)
                    base64PrivateKey = securityProperty.getMonTemporaryPrivateKey();
                else
                    base64PrivateKey = securityProperty.getMonDownPrivateKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_WCT:
                if(isTemporary)
                    base64PrivateKey = securityProperty.getWctTemporaryPrivateKey();
                else
                    base64PrivateKey = securityProperty.getWctDownPrivateKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_WCA:
                if(isTemporary)
                    base64PrivateKey = securityProperty.getWcaTemporaryPrivateKey();
                else
                    base64PrivateKey = securityProperty.getWcaDownPrivateKey();
                break;
            default:
                base64PrivateKey = "";
                break;
        }

        return new String(Base64.getDecoder()
                .decode(decrypt(data.getBytes(), base64PrivateKey)));
    }

    /**
     * 方法：获取临时加密公钥
     * @param appType 应用类型
     * @return 临时公钥
     */
    public String getTemporaryPublicKey(String appType) {
        String temporaryPublicKey;

        switch (appType) {
            case Constants.UTIL_SECURITY_APP_TYPE_APP:
                temporaryPublicKey = securityProperty.getAppTemporaryPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_WEB:
                temporaryPublicKey = securityProperty.getWebTemporaryPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_CMS:
                temporaryPublicKey = securityProperty.getCmsTemporaryPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_LED:
                temporaryPublicKey = securityProperty.getLedTemporaryPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_MON:
                temporaryPublicKey = securityProperty.getMonTemporaryPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_WCT:
                temporaryPublicKey = securityProperty.getWctTemporaryPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_WCA:
                temporaryPublicKey = securityProperty.getWcaTemporaryPublicKey();
                break;
            default:
                temporaryPublicKey = "";
                break;
        }

        return temporaryPublicKey;
    }

    /**
     * 方法：获取下行加密公钥
     * @param appType 应用类型
     * @return 下行公钥
     */
    public String getDownPublicKey(String appType) {
        String downPublicKey;

        switch (appType) {
            case Constants.UTIL_SECURITY_APP_TYPE_APP:
                downPublicKey = securityProperty.getAppDownPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_WEB:
                downPublicKey = securityProperty.getWebDownPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_CMS:
                downPublicKey = securityProperty.getCmsDownPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_LED:
                downPublicKey = securityProperty.getLedDownPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_MON:
                downPublicKey = securityProperty.getMonDownPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_WCT:
                downPublicKey = securityProperty.getWctDownPublicKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_WCA:
                downPublicKey = securityProperty.getWcaDownPublicKey();
                break;
            default:
                downPublicKey = "";
                break;
        }

        return downPublicKey;
    }

    /**
     * 方法：获取上行解密私钥
     * @param appType 应用类型
     * @return 解密私钥
     */
    public String getUpPrivateKey(String appType) {
        String upPrivateKey;

        switch (appType) {
            case Constants.UTIL_SECURITY_APP_TYPE_APP:
                upPrivateKey = securityProperty.getAppUpPrivateKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_WEB:
                upPrivateKey = securityProperty.getWebUpPrivateKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_CMS:
                upPrivateKey = securityProperty.getCmsUpPrivateKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_LED:
                upPrivateKey = securityProperty.getLedUpPrivateKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_MON:
                upPrivateKey = securityProperty.getMonUpPrivateKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_WCT:
                upPrivateKey = securityProperty.getWctUpPrivateKey();
                break;
            case Constants.UTIL_SECURITY_APP_TYPE_WCA:
                upPrivateKey = securityProperty.getWcaUpPrivateKey();
                break;
            default:
                upPrivateKey = "";
                break;
        }

        return upPrivateKey;
    }

    /**
     * 方法：系统加密
     * @param data 明文
     * @return 密文
     */
    public String encrypt4System(String data) {
        return encrypt(
                Base64.getEncoder().encode(data.getBytes()),
                securityProperty.getSysPublicKey());
    }

    /**
     * 方法：系统解密
     * @param data 密文
     * @return 明文
     */
    public String decrypt4System(String data) {
        return new String(Base64.getDecoder()
                .decode(decrypt(data.getBytes(),
                        securityProperty.getSysPrivateKey())));
    }
}
