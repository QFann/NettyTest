package com.qf.netty;


import com.alibaba.fastjson.JSON;

/**
 *  json 序列化
 * Created by qifan on 2018/9/27.
 */
public class JSONSerializer implements Serializer{
    /**
     *  获取算法标识
     * @return
     */
    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlgorithm.JSON;
    }

    /**
     *  将java对象序列化
     * @param object
     * @return
     */
    @Override
    public byte[] serializer(Object object) {
        return JSON.toJSONBytes(object);
    }

    /**
     *  将序列化数据转换成 java对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    @Override
    public <T> T deserializer(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }


}
