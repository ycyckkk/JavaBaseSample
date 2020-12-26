package com.yc.netty.oio;

import io.netty.util.CharsetUtil;

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
 * 异步网络编程
 *
 * 描述:通过选择器,监听通道事件进行读写.实现一个线程一个选择器,监听多个通道(客户端的请求注册到多路复用器,多路复用器进行轮询,当监听到有I/O请求时才启动线程处理)
 * 缺点:编程复杂
 * 同步非阻塞
 * 1.创建通道和选择器,配置并且注册通道(回调事件SelectionKey.OP_ACCEPT)
 * 2.从通道中获取服务端套接字,设置端口号
 * 3.循环监听事件,便利selectedKeys
 * 4.可连接时:获取ServerSocket,socket,设置socket
 * 5.可读取时写入:获取socket,byteBuffer
 * <p>
 * ServerSocketChannel.socket();
 * ByteBuffer msg = ByteBuffer.wrap("hello world".getBytes(CharsetUtil.UTF_8));
 * msg.duplicate()
 * selectionKey.channel();
 * (ByteBuffer) selectionKey.attachment()
 * <p>
 * ByteBuffer.hasRemain
 *
 * @Author yucheng
 * @Date 2020/12/26 17:02
 */
public class PlainNioServer {
    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null;
        SelectionKey selectionKey = null;

        try {
            //配置serverSocketChannel为非阻塞
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(Boolean.FALSE);

            //将serverSocketChannel注册到选择器中,准备接收连接
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress(1235));
            ByteBuffer msg = ByteBuffer.wrap("hello world".getBytes(CharsetUtil.UTF_8)); // TODO
            //等待需要处理的新事件;阻塞持续到下个事件传入
            for (; ; ) {
                selector.select();
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                while (iterator.hasNext()) {
                    selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = serverSocketChannel1.accept();
                        socketChannel.configureBlocking(Boolean.FALSE);
                        socketChannel.register(selector, SelectionKey.OP_READ, msg.duplicate());
                        System.out.println("Accepted from client =" + socketChannel);
//                        socketChannel.close();
                    } else if (selectionKey.isReadable()) {
                        //数据写入客户端
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                        while (byteBuffer.hasRemaining()) {
                            if (socketChannel.write(byteBuffer) == 0) {
                                break;
                            }
                        }
                        socketChannel.close();
                    }
                }
            }
            //遍历selector
            //检查事件是否已经就绪
            //接收客户端,并将通道注册到选择器中

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            selectionKey.cancel();
            try {
                selectionKey.channel().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
