package com.qf.netty;

/**
 * Created by qifan on 2018/9/27.
 */
public interface Serializer {


    byte JSON_SERIALIZER = 1 ;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlogrithm();


    /**
     *  java 对象转换成二进制
     * @param object
     * @return
     */
    byte[] serializer(Object object);

    /**
     * 二进制转换为java 对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserializer(Class<T> clazz,byte[] bytes);



}
