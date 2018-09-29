package com.qf.netty;

import io.netty.util.AttributeKey;

/**
 * Created by qifan on 2018/9/28.
 */
public interface Attributes {


    // 创建一个name为login的常量。
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");




}
