package com.example.others.netty.aio;

public class TimeServer {
    public static void main(String[] args) {
        int port =8080;
        AsyncTimeServerHandler timerServer = new AsyncTimeServerHandler(port);
        new Thread(timerServer,"TEST_AIO").start();
        
    }
}
