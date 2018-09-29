package com.qf.netty;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * Created by qifan on 2018/9/26.
 */
public class IOClient {

    public static void main(String[] args)throws Exception{
        new Thread(() ->{
            try {
                Socket socket = new Socket("127.0.0.1",8000);
                while (true){
                    try {
                        socket.getOutputStream().write((new Date()+": Hello World").getBytes());
                        Thread.sleep(2000);
                    }catch (Exception e){

                    }
                }
            }catch (IOException e){

            }
        }).start();
    }

}
