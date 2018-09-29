package com.qf.netty;

import lombok.Data;

import static com.qf.netty.Command.LOGIN_REQUEST;

/**
 *  登录请求数据包
 * Created by qifan on 2018/9/27.
 */
@Data
public class LoginRequestPacket extends Packet{

    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
