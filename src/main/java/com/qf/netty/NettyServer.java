package com.qf.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * Created by qifan on 2018/9/26.
 */
public class NettyServer {

    public static void main(String[] args){

        // 创建服务端引导类
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        // 创建两大线程组，boss 负责监听端口 worker负责 处理每一条连接的读写
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();


        serverBootstrap
                // 指定线程模型
                .group(boss,worker)
                // 指定IO 类型
                .channel(NioServerSocketChannel.class)
                // 每条连接的读写
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                // addLast（处理逻辑类）
//                ch.pipeline().addLast(new ServerHandler());
                ch.pipeline().addLast(new PacketDecoder());
                ch.pipeline().addLast(new LoginRequestHandler());
                ch.pipeline().addLast(new MessageRequestHandler());
                ch.pipeline().addLast(new PacketEncoder());
            }
        });
        // 绑定监听端口
        bind(serverBootstrap,8000);

    }

    /**
     *  监听端口
     * @param serverBootstrap  服务器类
     * @param port 端口
     */
    public static void bind(final ServerBootstrap serverBootstrap,final int port){
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("端口["+port+"]绑定成功!");
                }else {
                    //连接失败 ，端口+1 重试
                    System.out.println("端口["+ port + "]绑定失败!");
                    bind(serverBootstrap,port+1);
                }
            }
        });
    }


}
