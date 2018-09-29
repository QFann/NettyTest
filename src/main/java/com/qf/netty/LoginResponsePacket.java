package com.qf.netty;

import lombok.Data;

import static com.qf.netty.Command.LOGIN_RESPONSE;

/**
 *  响应数据包
 * Created by qifan on 2018/9/28.
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
