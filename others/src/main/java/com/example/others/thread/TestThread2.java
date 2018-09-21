package com.example.others.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class TestThread2 {
    public static void main(String[] args) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("syncTaildirCache-pool-%d").build();
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(1, namedThreadFactory);
        scheduledPool.scheduleAtFixedRate(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName());
                    } catch (Exception e) {

                    }
                }, 0, 1 * 1000, TimeUnit.MILLISECONDS
        );
    }
}
