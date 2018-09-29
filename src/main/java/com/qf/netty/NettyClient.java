package com.qf.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *  netty 客户端
 * Created by qifan on 2018/9/26.
 */
public class NettyClient {

    //最大连接重试次数
    public static final int MAX_RETRY = 5;

    public static void main(String[] args) throws InterruptedException {


        // 创建一个客户端引导类
        Bootstrap bootstrap = new Bootstrap();

        //创建一个线程组
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap
                //1.指定线程模型
                .group(group)
                //2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                //3.IO 处理逻辑  这里面是登陆以后的处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //采用责任链模式 ，
//                        ch.pipeline().addLast(new ClientHandler());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        // 连接服务器
     connect(bootstrap,"127.0.0.1",8000,MAX_RETRY);



    }

    /**
     *  连接服务器
     * @param bootstrap 客户端类
     * @param host 地址
     * @param port 端口
     * @param rerty 重试次数
     */
    public static void connect(Bootstrap bootstrap,String host,int port,int rerty){

        bootstrap.connect(host,port).addListener(future -> {
            if (future.isSuccess()){
                System.out.println("连接成功!");
                Channel channel = ((ChannelFuture)future).channel();
                //连接成功以后 执行会话
                startConsoleThread(channel);

            }else if(rerty == 0){
                System.out.println("连接次数已用完，放弃连接!");
            } else {
                //第几次重连
                int order  = (MAX_RETRY - rerty) + 1;
                //本次重连的间隔
                int delay = 1 << order;

                System.err.println(new Date() + ": 连接失败，第" + order + "次重连___" );

                bootstrap
                        .config()
                        .group()
                        .schedule(() -> connect(bootstrap,host,port,rerty - 1),delay, TimeUnit.SECONDS);

            }
        });


    }

    /**
     *  连接成功启动控制台会话
     * @param channel
     */
    private static void startConsoleThread(Channel channel) {

        new Thread(() ->{
            while (!Thread.interrupted()){
                if(LoginUtil.hasLogin(channel)){
                    System.out.println("输入消息发送至客户端: ");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();

//                    MessageRequestPacket packet = new MessageRequestPacket();
//                    packet.setMessage(line);
//
//                    ByteBuf buffer = PacketCodeC.INSTANCE.encode(channel.alloc(),packet);

                    channel.writeAndFlush(new MessageRequestPacket(line));

                }

            }


        }).start();


    }


}
