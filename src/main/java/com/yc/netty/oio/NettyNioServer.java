package com.yc.netty.oio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 观看创建服务端时序图
 *
 * 1) 创建引导器ServerBootstrap
 * 2) 绑定线程池EventLoopGroup
 * 3) 绑定服务端NioServerSocketChannel
 * 4) 设置通讯地址
 * 5) emmm....
 *
 *
 * io.netty.util.internal.PromiseNotificationUtil tryFailure(refCnt: 0, decrement: 1)
 *
 * @Author yucheng
 * @Date 2020/12/26 18:04
 */
public class NettyNioServer {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8"));
        EventLoopGroup eventLoopGroup = null;
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            eventLoopGroup = new NioEventLoopGroup();
            serverBootstrap.group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(1236))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            ChannelPipeline channelPipeline = socketChannel.pipeline();
                            channelPipeline.addLast(new ChannelInboundHandlerAdapter() {
//                                @Override
//                                public void channelActive(ChannelHandlerContext ctx) {
//                                    ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);
//                                }

                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                    cause.printStackTrace();
                                }

                                @Override
                                public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
                                }

                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    ByteBuf in = (ByteBuf) msg;
                                    System.out.println("Server received:" + in.toString(CharsetUtil.UTF_8));
                                    ctx.write(in);
                                }
                            });
//                    SocketChannel socketChannel1 = (SocketChannel) channelPipeline.channel();
//                    socketChannel1.write(buf);
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                eventLoopGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
