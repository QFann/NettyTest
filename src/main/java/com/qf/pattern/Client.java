package com.qf.pattern;

/**
 * Created by qifan on 2018/8/14.
 */
public class Client {
    public static void main (String[] args){
        Subject subject = new Proxy(new RealSubject());
        subject.request();
    }
}
