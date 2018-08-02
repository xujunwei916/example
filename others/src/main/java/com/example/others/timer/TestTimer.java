package com.example.others.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {
    public static void main(String[] args) {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            int a = 1;
            @Override
            public void run() {
                a += 1;
                System.out.println(a);
                if (a == 10) {
                    cancel();
                    timer.cancel();
                }
            }
        };
        // delay：用户调用 schedule() 方法后，要等待这么长的时间才可以第一次执行run() 方法
        // period：第一次调用之后，从第二次开始每隔多长的时间调用一次 run() 方法
        timer.schedule(task, 1000, 1000);
//        timer.cancel();
    }
}
