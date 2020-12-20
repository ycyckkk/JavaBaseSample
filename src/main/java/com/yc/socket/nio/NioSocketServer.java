package com.yc.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author yucheng
 * @Date 2020/12/20 14:52
 */
public class NioSocketServer {
    //Selector
    //ServerSocketChannel，设置非阻塞并且开始监听
    //ServerSocket
    //bind InetSocketAddress

    public static void main(String[] args) {
        try {
                //监听选择器，一个thread使用一个selector，通过轮询监听不同多个channel。从而达到一个线程可以处理多个事件
            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(Boolean.FALSE);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress("127.0.0.1", 1314));
            while (true) {
                //ss
                //监听事件
                selector.select();
                //ss
                //事件到达
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                //ki
                //kn
                //if ki ssc
                //循环多个事件
                while (iterator.hasNext()) {
                    //取出具体的监听事件
                    SelectionKey selectionKey = iterator.next();
                    //连接事件
                    if (selectionKey.isAcceptable()) {
                        // TODO
                        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel1 = channel.accept();
                        socketChannel1.configureBlocking(Boolean.FALSE);
                        socketChannel1.register(selector, SelectionKey.OP_READ);
                        System.out.println("123");
                        //读取事件
                    } else if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        System.out.println("456");
                        System.out.println("accept = " + readDataFromSocket(channel));
                        // TODO
                        channel.close();
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String readDataFromSocket(SocketChannel socketChannel) {
        //BB
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        StringBuilder stringBuilder = new StringBuilder();
        //SB

        try {
            while (true) {
                //bc
                byteBuffer.clear();
                int count = socketChannel.read(byteBuffer);
                if (count == -1) {
                    break;
                }
                byteBuffer.flip();

                int limit = byteBuffer.limit();
                char[] chars = new char[limit];
                for (int i = 0; i < limit; i++) {
                    chars[i] = (char) byteBuffer.get(i);
                }
                stringBuilder.append(chars);
                byteBuffer.clear();
                return stringBuilder.toString();
                //cr
                //flip
                //limit
                //char[]
                //bg
                //app
                //clear

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
