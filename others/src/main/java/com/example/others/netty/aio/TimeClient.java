package com.example.others.netty.aio;

public class TimeClient {
    public static void main(String[] args) {
        int port =8080;
        for (int i = 0; i < 100000; i++) {
            AsyncTimeClientHandler timeClinet = new AsyncTimeClientHandler("127.0.0.1",port);
            new Thread(timeClinet,"TEST_AIO").start();
        }

        
    }
}
