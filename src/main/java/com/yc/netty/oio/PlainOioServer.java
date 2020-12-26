package com.yc.netty.oio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 阻塞网络编程
 *
 * BIO、NIO、AIO分别是什么？
 * server端read
 * 遇到情况:怎么维持长连接
 * @Author yucheng
 * @Date 2020/12/26 16:27
 */
public class PlainOioServer {
    public static void main(String[] args) throws Exception {
        //此处阻塞
        ServerSocket serverSocket = new ServerSocket(1234);
        for (; ; ) {
            //阻塞
            Socket socket = serverSocket.accept();
            //每次请求过来,都需要新建一个线程去处理
            new Thread(new Runnable() {
                @Override
                public void run() {
                    InputStream inputStream = null;
//            OutputStream outputStream = null;
                    try {
                        inputStream = socket.getInputStream();
                        InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
//            BufferedInputStream bis = new BufferedInputStream(isr);
                        BufferedReader br = new BufferedReader(isr);
                        String str;
                        //线程阻塞
                        while ((str = br.readLine()) != null) {
                            System.out.println("server received:  " + str);
                        }
//                outputStream = socket.getOutputStream();
//                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//                bufferedWriter.write("this is oio");
//                bufferedWriter.flush();
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
//                    outputStream.close();
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();


        }

    }
}
