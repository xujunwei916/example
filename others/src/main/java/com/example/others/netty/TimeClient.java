package com.example.others.netty;

/**
 * 时间客户端
 */
public class TimeClient {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            int port = 8088;
            new Thread(new TimeClientHandler("127.0.0.1", port)).start();

        }


    }
}
