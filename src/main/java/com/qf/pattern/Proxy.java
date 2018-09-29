package com.qf.pattern;

/**
 * Created by qifan on 2018/8/14.
 */
public class Proxy implements Subject{

    private RealSubject realSubject;

    public Proxy(RealSubject realSubject){
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        System.out.println("####### before");
        try{
            realSubject.request();
        }catch (Exception e){
            System.out.println("ex "+e.getMessage());
            try {
                throw e;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }finally {
            System.out.println("#### after");
        }

    }

    @Override
    public void hello() {

    }
}
