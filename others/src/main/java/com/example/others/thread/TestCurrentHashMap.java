package com.example.others.thread;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class TestCurrentHashMap {


    public static void main(String[] args) {

        ConcurrentHashMap<Integer, String> conMap = new ConcurrentHashMap<>();


         new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    for (int i = 0; i <   new Random().nextInt(1000); i++) {
                       int v= new Random().nextInt(1000);
                        conMap.put(v, v +"");
                    }
                    try {
                        Thread.sleep(1L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Iterator<Integer> iterator = conMap.keySet().iterator();

                    while (iterator.hasNext()) {


                        String value = conMap.get(iterator.next());
//                        System.out.println(value);
                        System.out.println(conMap.size());
                        conMap.remove(Integer.valueOf(value));

                    }

                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        for (int i = 0; i <1000 ; i++) {

        }


    }
}
