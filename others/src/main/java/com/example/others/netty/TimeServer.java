package com.example.others.netty;

public class TimeServer {
    public static void main(String[] args) throws Exception {
        int port = 8088;

        MultiplexerTimeServer server = new MultiplexerTimeServer(port);
        new Thread(server, "NIO-0001").start();


    }
}
