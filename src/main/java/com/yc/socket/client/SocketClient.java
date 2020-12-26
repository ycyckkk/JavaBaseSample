package com.yc.socket.client;

import java.io.*;
import java.net.Socket;

/**
 * 客户端write
 * @Author yucheng
 * @Date 2020/12/20 11:56
 */
public class SocketClient {
    public static void main(String[] args) {
        Socket socket = null;
//        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket("127.0.0.1", 1234);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("client info");
            out.flush();
//            String response;
//            while ((response = in.readLine()) != null) {
//                System.out.println(response);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
//                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
