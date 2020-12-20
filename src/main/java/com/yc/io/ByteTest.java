package com.yc.io;

import java.io.*;

/**
 * @Author yucheng
 * @Date 2020/12/20 13:56
 */
public class ByteTest {
    public static void main(String[] args) {
        copyFile("D:\\BaiduNetdiskDownload\\A.txt", "D:\\BaiduNetdiskDownload\\B.txt");
    }
    public static void copyFile(String src, String dest) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(src);
            fileOutputStream = new FileOutputStream(dest);
            byte[] buffer = new byte[1024 * 2 * 2];
            int index;
            while ((index = fileInputStream.read(buffer, 0, buffer.length)) != -1) {
                fileOutputStream.write(buffer, 0, buffer.length);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}