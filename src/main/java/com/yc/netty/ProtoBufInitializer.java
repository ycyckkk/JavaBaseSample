package com.yc.netty;

import com.google.protobuf.MessageLite;
import io.netty.channel.*;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

/**
 * @Author yucheng
 * @Date 2020/12/23 23:25
 */
public class ProtoBufInitializer extends ChannelInitializer<Channel> {

    private final MessageLite obj;

    public ProtoBufInitializer(MessageLite obj) {
        this.obj = obj;
    }

    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new ProtobufDecoder(obj));
        pipeline.addLast(new ObjectHandler());
    }
    public static final class ObjectHandler extends SimpleChannelInboundHandler<Object> {

        protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

        }
    }
}
