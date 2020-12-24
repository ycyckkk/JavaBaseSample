package com.yc.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AES加密
 *
 * @Author yucheng
 * @Date 2020/12/24 21:29
 */
public class AesTest {
    public static void main(String[] args) throws Exception {
        String message = "Hello, world!";
        System.out.println("Message: " + message);

        byte[] data = message.getBytes("UTF-8");
        byte[] key = "1234567890abcdef".getBytes();//固定位数的加密串
        //加密
        byte[] encrypted = encrypt(key, data);
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
        //解密
        byte[] decrypted = decrypt(key, encrypted);
        System.out.println("Decrypted: " + new String(decrypted, "UTF-8"));

    }


    //加密
    public static byte[] encrypt(byte[] key, byte[] input) throws Exception {
        //生成密码
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        //密钥
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(input);
    }

    //解密
    public static byte[] decrypt(byte[] key, byte[] input) throws Exception {
        //生成密码
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        //密钥
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(input);
    }
}
