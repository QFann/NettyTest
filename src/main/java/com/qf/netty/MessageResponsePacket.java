package com.qf.netty;

import lombok.Data;

import static com.qf.netty.Command.MESSAGE_RESPONSE;

/**
 *  消息响应数据包
 * Created by qifan on 2018/9/28.
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
