package com.qf.chain;

/**
 * Created by qifan on 2018/8/15.
 */
public class Client {

    static class HandlerA extends Handker{

        @Override
        protected void handleProcess() {
            System.out.println("handler by a");
        }
    }

    static class HandlerB extends Handker{

        @Override
        protected void handleProcess() {
            System.out.println("handler by b");
        }
    }

    static class HandlerC extends Handker{

        @Override
        protected void handleProcess() {
            System.out.println("handler by c");
        }
    }

    public static void main(String[] args){
        Handker handkerA = new HandlerA();
        Handker handkerB = new HandlerB();
        Handker handkerC = new HandlerC();

        handkerA.setSucessor(handkerB);
        handkerB.setSucessor(handkerC);

        handkerA.execute();

    }
}
