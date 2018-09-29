package com.qf.cglib;

import com.qf.pattern.RealSubject;
import com.qf.pattern.Subject;
import org.springframework.cglib.proxy.Enhancer;

/**
 * Created by qifan on 2018/8/14.
 */
public class Client {

    public static void main (String[] args){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(new DemoMethodInterceptor());
        Subject subject = (Subject) enhancer.create();
        subject.request();
        subject.hello();
    }
}
