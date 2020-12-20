package com.yc.nio;

import io.netty.buffer.ByteBuf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author yucheng
 * @Date 2020/12/20 14:38
 */
public class NioTest1 {
    public static void main(String[] args) {
        fastCopy("D:\\BaiduNetdiskDownload\\A.txt", "D:\\BaiduNetdiskDownload\\F.txt");
    }

    public static void fastCopy(String source, String dest) {
        //fis
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(source);
            FileChannel fileChannel1 = fis.getChannel();//面向通道
            fos = new FileOutputStream(dest);
            FileChannel fileChannel2 = fos.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);//缓存区
            while (true) {
                int r = fileChannel1.read(byteBuffer);
                if (r == -1) {
                    break;
                }
                byteBuffer.flip();//切换方向
                fileChannel2.write(byteBuffer);
                byteBuffer.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //fc1
        //fos
        //fc2
        //bb


    }
}
