package com.qf.netty;

import lombok.Data;

import static com.qf.netty.Command.MESSAGE_REQUEST;

/**
 *  消息发送数据包
 * Created by qifan on 2018/9/28.
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    public MessageRequestPacket(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
