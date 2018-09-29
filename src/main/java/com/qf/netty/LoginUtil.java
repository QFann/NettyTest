package com.qf.netty;

import io.netty.channel.Channel;
import io.netty.util.Attribute;


/**
 * Created by qifan on 2018/9/28.
 */
public class LoginUtil {

    /**
     *  已登录，设置login 为 true
     * @param channel 通道
     */
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    /**
     * 判断是否登录
     * @param channel 通道
     * @return
     */
    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }

}
