package com.yc.socket.client;

import java.io.*;
import java.net.Socket;

/**
 * @Author yucheng
 * @Date 2020/12/20 11:56
 */
public class SocketClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("client info");
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
