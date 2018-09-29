package com.qf.dynamic;

import com.qf.pattern.RealSubject;
import com.qf.pattern.Subject;

import java.lang.reflect.Proxy;

/**
 * Created by qifan on 2018/8/14.
 */
public class Client {

    public static void main (String[] args){
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        Subject subject =(Subject) Proxy.newProxyInstance(Client.class.getClassLoader(),new Class[]{Subject.class},new JdkProxySubject(new RealSubject()));
        subject.request();
    }


}
