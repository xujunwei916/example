package com.example.others.http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class TestQPS2 {

    public static void main(String[] args) throws Exception {

        ExecutorService POOL = Executors.newFixedThreadPool(50);
        LinkedBlockingQueue<String> result = new LinkedBlockingQueue<String>();
        long start  = System.currentTimeMillis();
        for (int i = 0; i < 100000 ; i++) {

            POOL.submit(new Runnable() {

                @Override
                public void run() {
                    String response = HttpRequest.sendGet("http://www.adintercept.com",null);
                    result.add(response);
                }
            });
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
