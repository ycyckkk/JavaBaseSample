package com.yc.io;

import org.apache.tools.ant.util.ReaderInputStream;
import sun.management.snmp.util.SnmpTableCache;

import java.io.*;

/**
 * @Author yucheng
 * @Date 2020/12/20 14:11
 */
public class ReaderTest {
        public static void main(String[] args) {
        copyFile("D:\\BaiduNetdiskDownload\\A.txt", "D:\\BaiduNetdiskDownload\\C.txt");
    }

    public static void copyFile(String src, String dest) {
        FileReader reader = null;
        FileWriter writer = null;
        try {
            reader = new FileReader(src);
            writer = new FileWriter(dest);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String info;
            while ((info = bufferedReader.readLine()) != null) {
                writer.write(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
