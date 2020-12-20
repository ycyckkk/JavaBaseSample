package com.yc.io;

import java.io.File;

/**
 * 基于文件的操作
 * @Author yucheng
 * @Date 2020/12/20 13:52
 */
public class FileTest {
    public static void main(String[] args) {
        File file = new File("D:\\BaiduNetdiskDownload");
        listAllFiles(file);
    }

    public static void listAllFiles(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }
        if (dir.isFile()) {
            System.out.println("fileName= " + dir.getName());
            return;
        }
        for (File file : dir.listFiles()) {
            listAllFiles(file);
        }
    }
}
