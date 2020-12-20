package com.yc.socket.nio;


import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author yucheng
 * @Date 2020/12/20 14:52
 */
public class NioSocketClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 1314);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("hello world".getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
