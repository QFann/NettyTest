package com.qf.netty;

/**
 *  指令类
 * Created by qifan on 2018/9/27.
 */
public interface Command {

    // 登录请求
    Byte LOGIN_REQUEST = 1;

    //登录响应
    Byte LOGIN_RESPONSE = 2;

    //请求消息
    Byte MESSAGE_REQUEST = 3;

    //响应消息
    Byte MESSAGE_RESPONSE = 4;

}
