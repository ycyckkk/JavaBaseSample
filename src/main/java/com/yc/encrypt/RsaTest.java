package com.yc.encrypt;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * @Author yucheng
 * @Date 2020/12/24 21:41
 */
public class RsaTest {
    public static void main(String[] args) throws Exception {

        String message = "helloWolrd";
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair pair = kpg.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        //公钥加密
        System.out.println(String.format("publicKey: %x", new BigInteger(1, publicKey.getEncoded())));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypted = cipher.doFinal(message.getBytes("UTF-8"));
        System.out.println(String.format("encrypted: %x", new BigInteger(1, encrypted)));

        //私钥解密
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decrypted = cipher.doFinal(encrypted);
        System.out.println(String.format(new String(decrypted, "UTF-8")));

        //待签名的消息
        byte[] message1 = "Hello, I am Bob!".getBytes(StandardCharsets.UTF_8);
        Signature s = Signature.getInstance("SHA1withRSA");
        s.initSign(privateKey);
        s.update(message1);
        byte[] signed = s.sign();
        System.out.println(String.format("signature: %x", new BigInteger(1, signed)));

        //签名验证
        Signature v = Signature.getInstance("SHA1withRSA");
        v.initVerify(publicKey);
        v.update(message1);
        boolean valid = v.verify(signed);
        System.out.println("签名验证结果valid=" + valid);


    }
}