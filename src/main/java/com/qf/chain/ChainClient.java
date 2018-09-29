package com.qf.chain;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
/**
 * Created by qifan on 2018/8/17.
 */
public class ChainClient {

    static class ChainHandlerA extends ChainHandler{

        @Override
        protected void handleProcess() {
            System.out.println("handler by Chain a");
        }
    }

    static class ChainHandlerB extends ChainHandler{

        @Override
        protected void handleProcess() {
            System.out.println("handler by Chain b");
        }
    }

    static class ChainHandlerC extends ChainHandler{

        @Override
        protected void handleProcess() {
            System.out.println("handler by Chain c");
        }
    }

    public static void main(String[] args){
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        List<ChainHandler> chainHandlers = Arrays.asList(
          new ChainHandlerA(),
          new ChainHandlerB(),
          new ChainHandlerC()
        );
        Chain chain = new Chain(chainHandlers);
        chain.proceed();


    }

}
