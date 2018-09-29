package com.qf.pattern;

/**
 * Created by qifan on 2018/8/14.
 */
public class RealSubject implements Subject{

    @Override
    public void request() {
        System.out.println("Real Subject execute request");
    }

    @Override
    public void hello() {
        System.out.println("hello");
    }
}
