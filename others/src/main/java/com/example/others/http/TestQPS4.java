package com.example.others.http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class TestQPS4 {

    public static void main(String[] args) throws Exception {

        ExecutorService POOL = Executors.newFixedThreadPool(500);
        LinkedBlockingQueue<String> result = new LinkedBlockingQueue<String>();
        long start  = System.currentTimeMillis();
        for (int i = 0; i < 70000 ; i++) {

            POOL.submit(new Runnable() {

                @Override
                public void run() {
                    String response = HttpRequest.sendGet("http://192.168.65.131:8084/test_thread",null);
                    result.add(response);
                }
            });
//            POOL.submit(new Runnable() {
//
//                @Override
//                public void run() {
//                    String response = HttpRequest.sendGet("http://192.168.65.131:8083/test_thread",null);
//                    result.add(response);
//                }
//            });
        }
        POOL.shutdown();
        while (true) {
            if (POOL.isTerminated()) {
                System.out.println("结束了！");
                break;
            }
            Thread.sleep(20);
        }
        System.out.println(System.currentTimeMillis()-start);






    }

}
