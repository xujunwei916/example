package com.example.others;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class TestThread {

    private Set<String> set = new HashSet<String>();


    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }


    public static void main(String[] args) {
        TestThread testThread = new TestThread();


        Timer timer = new Timer();
        TimerTask task = new TimerTask() {


            @Override
            public void run() {
                Set<String> set =  new HashSet<>();
                set.add("aaaa");
                set.add("aaaa");
                testThread.setSet(set);
//                System.out.println("laod");
            }
        };
        // delay：用户调用 schedule() 方法后，要等待这么长的时间才可以第一次执行run() 方法
        // period：第一次调用之后，从第二次开始每隔多长的时间调用一次 run() 方法
        timer.schedule(task, 10, 10);

        long count =0L;

        while (true){
            testThread.getSet().contains("aaaaa");
            count++;
            if(count%1000000==0){
                System.out.println(count);
            }
        }
    }



}
