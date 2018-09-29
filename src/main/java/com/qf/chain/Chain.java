package com.qf.chain;

import java.util.List;

/**
 * Created by qifan on 2018/8/15.
 */
public class Chain {

    private List<ChainHandler> chainHandler;

    private int index = 0;

    public Chain(List<ChainHandler> chainHandler){
        this.chainHandler = chainHandler;
    }

    public void proceed(){
        if(index >= chainHandler.size()){
            return;
        }
        chainHandler.get(index++).execute(this);
    }

}
