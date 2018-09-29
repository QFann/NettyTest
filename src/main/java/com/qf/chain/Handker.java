package com.qf.chain;

/**
 * Created by qifan on 2018/8/15.
 */
public abstract class Handker {

    private Handker sucessor;

    public Handker getSucessor() {
        return sucessor;
    }

    public void setSucessor(Handker sucessor) {
        this.sucessor = sucessor;
    }

    public void execute(){
        handleProcess();
        if(sucessor != null){
            sucessor.execute();
        }
    }

    protected abstract void handleProcess();

}
