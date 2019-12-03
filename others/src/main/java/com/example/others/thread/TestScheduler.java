package com.example.others.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TestScheduler {

    public static void main(String[] args) {
         ScheduledFuture<?> timedFuture;
         ScheduledExecutorService timedPool;
        timedPool = Executors.newScheduledThreadPool(1,
                new ThreadFactoryBuilder().setNameFormat("ssss").build());

        timedFuture = timedPool.scheduleWithFixedDelay(new Runnable() {
            public void run() {

                System.out.println(111);
            }

        }, 30, 30, TimeUnit.SECONDS);

    }
}
