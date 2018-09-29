package com.qf.netty;

import lombok.Data;

/**
 * Created by qifan on 2018/9/27.
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    private Byte version = 1;


    public abstract Byte getCommand();


}
