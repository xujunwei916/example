package com.example.others;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class TestThread {

//    private Set<String> set = new HashSet<String>();
//
//
//    public Set<String> getSet() {
//        return set;
//    }
//
//    public void setSet(Set<String> set) {
//        this.set = set;
//    }
    private int a =10;


    public static void main(String[] args) {
        TestThread testThread = new TestThread();


        Timer timer = new Timer();
        TimerTask task = new TimerTask() {


            @Override
            public void run() {
//                Set<String> set =  new HashSet<>();
//                set.add("aaaa");
//                set.add("aaaa");
//                testThread.setSet(set);
//                System.out.println("laod");
                testThread.a=10;
            }
        };
        // delay：用户调用 schedule() 方法后，要等待这么长的时间才可以第一次执行run() 方法
        // period：第一次调用之后，从第二次开始每隔多长的时间调用一次 run() 方法
        timer.schedule(task, 1, 1);

        long count =0L;

        while (true){

            if(testThread.a!=10){
                throw new RuntimeException("eeeeee");
            }
//            System.out.println(testThread.a);
        }
    }



}
