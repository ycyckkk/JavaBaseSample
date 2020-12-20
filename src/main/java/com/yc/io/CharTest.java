package com.yc.io;


import java.io.UnsupportedEncodingException;

/**
 * 操作字符
 * @Author yucheng
 * @Date 2020/12/20 14:04
 */
public class CharTest {
    public static void main(String[] args) {
        String name = "老高";
        try {
            byte[] bytes = name.getBytes("UTF-8");
            System.out.println(bytes.toString());

            String newString = new String(bytes, "UTF-8");
            System.out.println(newString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
