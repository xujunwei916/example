package com.example.others.netty.msgpack;

import org.msgpack.MessagePack;
import org.msgpack.type.Value;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoServerHandler extends ChannelHandlerAdapter {
    private int counter = 0;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("This is " + ++counter + " times receive client : [" + msg + "]");
        System.out.println(msg.getClass());
        Value body = (Value) msg;

        MessagePack msgPack = new MessagePack();
        UserInfo info =  msgPack.convert(body,UserInfo.class);
        System.out.println(info);
        ctx.writeAndFlush(msg);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }
}
