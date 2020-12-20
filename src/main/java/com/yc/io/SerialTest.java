package com.yc.io;

import java.io.*;

/**
 * 序列化测试
 *
 * @Author yucheng
 * @Date 2020/12/20 14:16
 */
public class SerialTest {
    public static void main(String[] args) {
        A a1 = new A(123, "test");

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\BaiduNetdiskDownload\\D.txt"));
            objectOutputStream.writeObject(a1);
            objectOutputStream.close();

            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:\\BaiduNetdiskDownload\\D.txt"));
            A a2 = (A) objectInputStream.readObject();
            System.out.println(a2.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static class A implements Serializable  {
        private int x;
        private String y;

        public A(int x, String y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "A{" +
                    "x=" + x +
                    ", y='" + y + '\'' +
                    '}';
        }
    }
}
