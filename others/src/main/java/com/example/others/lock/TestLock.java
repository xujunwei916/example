package com.example.others.lock;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;

public class TestLock {

    private final Object lock = new Object();

    private boolean terminated = false;

    public static void main(String[] args) throws Exception {

        TestLock testLock = new TestLock();


        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0 ;
                while (true) {

                    String s = null;
                    try {
                        s = IOUtils.toString(new FileReader("D://test//lock.txt"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (s.equalsIgnoreCase("stop")) {
                        testLock.stop("ss");
                    }
                    System.out.println(i++);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();



/**
 *  register hooks, which are called before the process exits
 */
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Kill by kill cmd");
            }
        }));

        testLock.awaitTermination();

    }

    public synchronized void stop(String cause) {
        synchronized (lock) {
            terminated = true;
            lock.notifyAll();
        }
    }

    public void awaitTermination() throws InterruptedException {
        synchronized (lock) {
            while (!terminated) {
                System.out.println("start");
                lock.wait();
                System.out.println("stop");
            }
        }
    }
}
