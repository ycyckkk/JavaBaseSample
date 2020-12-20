package com.yc.socket.server;

import com.sun.security.ntlm.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 每个连接都需要单独的线程处理，线程过多会增加系统的开销（内存占用和上下文切换）
 * @Author yucheng
 * @Date 2020/12/20 11:50
 */
public class SocketServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            Socket socket = serverSocket.accept();//该方法会一直阻塞调用

            //从字符输入流中读取文本
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //将打印对象的格式化表示到文本输入流
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String request, response;
            //读取的方法将会一直阻塞
            while ((request = in.readLine()) != null) {
                if ("Done".equals(request)) {
                    break;
                }
                response = "accept " + request + "," + "hello this is server";
                out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
