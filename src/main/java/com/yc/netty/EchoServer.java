package com.yc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Author yucheng
 * @Date 2020/12/20 12:24
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
//        if (args.length != 1) {
//            System.err.println("Usage：" + EchoServer.class.getSimpleName() + "<port>");
//            return;
//        }
        int port = 11233;
        new EchoServer(port).start();
    }

    public void start() {
        //创建ServerHandler，EventGroupLoop，ServerBootStrap
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            final EchoServerHandler echoServerHandler = new EchoServerHandler();
            ServerBootstrap bootstrap = new ServerBootstrap();
            //b.g c l ch
            bootstrap.group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class) //基于server通道
                    .localAddress(new InetSocketAddress(port))//绑定IP地址
                    .childHandler(new ChannelInitializer<SocketChannel>() {//通道处理器初始化通道信息
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(echoServerHandler);
                        }
                    });
            //b b s
            ChannelFuture future = bootstrap.bind().sync();
            //c c s
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                eventLoopGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //f c c s
    }
}
