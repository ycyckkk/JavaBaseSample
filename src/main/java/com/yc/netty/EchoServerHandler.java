package com.yc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * CHA
 * 所有的操作将转发到此处，重写三个方法
 *
 * @Author yucheng
 * @Date 2020/12/20 12:11
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    //从通道中读取数据
    //server的读写都在一个方法
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server received:" + in.toString(CharsetUtil.UTF_8));
        //将消息写给发送者
        ctx.write(in);
    }

    //从通道中将数据读取完成
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //关闭通道
        //将未决消息冲刷到远程节点
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }


    //异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
