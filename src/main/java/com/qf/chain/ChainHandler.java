package com.qf.chain;

/**
 * Created by qifan on 2018/8/15.
 */
public abstract class ChainHandler {

    public void execute(Chain chain){
        handleProcess();
        chain.proceed();
    }

    protected abstract void handleProcess();

}
