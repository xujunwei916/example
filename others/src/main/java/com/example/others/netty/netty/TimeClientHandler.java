package com.example.others.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter {

    private final ByteBuf firstMesssage;
    private byte[] req;
    private int count = 0;

    public TimeClientHandler() {
        req = ("query time order" + System.getProperty("line.separator")).getBytes();
        firstMesssage = Unpooled.buffer(req.length);
        firstMesssage.writeBytes(req);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {

            message = Unpooled.copiedBuffer(req);
            ctx.writeAndFlush(message);

        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] resp = new byte[buf.readableBytes()];
//        buf.readBytes(resp);
//        String body = new String(resp, "utf8");
        String body = (String) msg;
        System.out.println("Now is " + body + " , count = " + ++count);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
