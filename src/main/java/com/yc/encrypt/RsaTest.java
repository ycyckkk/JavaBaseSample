package com.yc.encrypt;

import javax.crypto.Cipher;
import java.math.BigInteger;
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
    }
}