package com.yc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 客户端的简单回调处理器   SCH
 * @Author yucheng
 * @Date 2020/12/20 12:30
 */
public class EchoClientHanlder extends SimpleChannelInboundHandler<ByteBuf> {

    //通道激活后开始进行写
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty Work", CharsetUtil.UTF_8));
    }

    //读取数据
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        //怎么读byteBuf呢？
        System.out.println("client receiver:" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
