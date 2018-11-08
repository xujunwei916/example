package com.example.others.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThread {

    public static void main(String[] args) {
      ExecutorService POOL = Executors.newScheduledThreadPool(3);
      for (int i=0;i<1000;i++){
      POOL.submit(new Runnable() {
          @Override
          public void run() {

              try {
                  Thread.sleep(1000L);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println(Thread.currentThread().getName()+":你好");
          }
      });}
        System.out.println("submit ok");
      POOL.shutdown();
    }





}
