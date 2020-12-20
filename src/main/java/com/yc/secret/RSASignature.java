package com.yc.secret;

import java.nio.charset.Charset;
import java.security.*;

/**
 * @Author yucheng
 * @Date 2020/12/7 20:13
 */
public class RSASignature {

    public static void main(String[] args) throws Exception {
        //获取公钥和私钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        //加密串
        byte[] message = "I AM YUCHENG".getBytes("UTF-8");

        //私钥签名


    }
}
