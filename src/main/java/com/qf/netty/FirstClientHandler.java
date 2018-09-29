package com.qf.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * Created by qifan on 2018/9/27.
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println(new Date() + ": 客户端写出数据");

        //1.读取数据
        ByteBuf buf = getByteBuf(ctx);

        //2.写数据
        ctx.channel().writeAndFlush(buf);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        ByteBuf buf = (ByteBuf)msg;

        System.out.println(new Date() + ": 服务端发送过来的数据!" + buf.toString(Charset.forName("utf-8")));

    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        //1.获取二进制抽象ByteBuf
        ByteBuf buf = ctx.alloc().buffer();

        //2.准备数据，指定字符串的字符集为utf-8
        byte[] bytes = "你好，qfann!".getBytes(Charset.forName("utf-8"));

        //3.填充数据到ByteBuf
        buf.writeBytes(bytes);

        return buf;

    }

}
