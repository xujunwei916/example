package com.example.spark.test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestTime {
    public static void main(String[] args) {

        Timer timer = new Timer();
        TimerTask task =new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new Date());
            }
        };
        timer.schedule(task,1000,3000);

    }
}
