package com.yc.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author yucheng
 * @Date 2020/12/26 13:38
 */
public class NioTest2 {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File("D://BaiduNetdiskDownload//F.txt"));
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            int i;
            i = fileChannel.read(byteBuffer);
            if (i < 1) {
                break;
            }
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array()));
            byteBuffer.clear();
        }
    }
}
