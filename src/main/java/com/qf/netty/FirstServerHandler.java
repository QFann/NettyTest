package com.qf.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * Created by qifan on 2018/9/27.
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        ByteBuf buf = (ByteBuf)msg;

        System.out.println(new Date() + ": 服务端读到数据 -> "+ buf.toString(Charset.forName("utf-8")));

        ByteBuf out = getByteBuf(ctx);

        System.out.println(new Date() + ": 服务端写出数据 ");

        ctx.channel().writeAndFlush(out);

    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        byte[] bytes = "hello,qfann ! 我收到了你的信息".getBytes(Charset.forName("utf-8"));

        ByteBuf buf = ctx.alloc().buffer();

        buf.writeBytes(bytes);

        return buf;

    }

}
