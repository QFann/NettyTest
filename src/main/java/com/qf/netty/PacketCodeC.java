package com.qf.netty;

import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.qf.netty.Command.*;

/**
 * Created by qifan on 2018/9/27.
 */
public class PacketCodeC {

    // 魔数，用来判断是否符合此协议
    private static final int MAGIC_NUMBER = 0x12345678;
    // 单例模式
    public static final PacketCodeC INSTANCE = new PacketCodeC();
    //数据包类型
    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    // 序列化
    private final Map<Byte, Serializer> serializerMap;

//    static {
//        packetTypeMap = new HashMap<>();
//        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
//
//        serializerMap = new HashMap<>();
//        Serializer serializer = new JSONSerializer();
//        serializerMap.put(serializer.getSerializerAlogrithm(), serializer);
//    }
    //初始化构造器
    private PacketCodeC(){
        packetTypeMap = new HashMap<>();
        //客户端登陆请求
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        //服务端登录响应
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        //客户端发送消息
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        //服务端消息响应
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);


        serializerMap = new HashMap<>();

        // 采用json 的序列化
        Serializer serializer = new JSONSerializer();
        // 序列化算法标识
        serializerMap.put(serializer.getSerializerAlogrithm(), serializer);
    }

    /**
     *  编码过程
     * @param byteBuf  字节容器
     * @param packet  数据包
     * @return
     */
    public static ByteBuf encode(ByteBuf byteBuf,Packet packet){
        //1.创建 ByteBuf 对象
//        ByteBuf byteBuf = byteBufAllocator.ioBuffer();

        //2.序列化 java 对象  将传进来的数据包序列化
        byte[] bytes = Serializer.DEFAULT.serializer(packet);

        //3.实际编码过程  操作传进来的ByteBuf 字节容器
        byteBuf.writeInt(MAGIC_NUMBER);   // 魔数
        byteBuf.writeByte(packet.getVersion()); //版本号
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm()); // 算法标识
        byteBuf.writeByte(packet.getCommand()); //指令
        byteBuf.writeInt(bytes.length); //传输数据的长度
        byteBuf.writeBytes(bytes); //数据

        return byteBuf;

    }

    /**
     *  解码过程
     * @param byteBuf 字节容器
     * @return
     */
    public Packet decode(ByteBuf byteBuf){
        // 这里没做魔数校验， 跳过 magic_number   可以自行加逻辑，取出魔数跟预定义的作对比，校验请求协议是否符合
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 获取序列化算法标识
        byte serializeAlgotithm = byteBuf.readByte();

        //获取 指令
        byte command = byteBuf.readByte();

        // 获取数据包长度
        int length = byteBuf.readInt();

        //创建一个符合数据长度的Bytes
        byte[] bytes = new byte[length];

        //读取数据
        byteBuf.readBytes(bytes);

        // 根据指令判断数据包类型
        Class<? extends Packet> requestType = getRequestType(command);

        // 根据序列化算法标识获取序列化方式
        Serializer serializer = getSerializer(serializeAlgotithm);


        if (requestType != null && serializer != null) {
            // 将序列化数据转化成 java 对象
            return serializer.deserializer(requestType, bytes);
        }

        return null;
    }

    /**
     *  返回序列化方式
     * @param serializeAlgorithm 算法标识
     * @return
     */
    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    /**
     *  获取数据包类型
     * @param command 指令
     * @return
     */
    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }

}
