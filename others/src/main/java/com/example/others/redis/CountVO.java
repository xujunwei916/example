package com.example.others.redis;

public class CountVO {
    private  int countAll=0;
    private int  countRes=0;

    synchronized public void setCountAll(int countAll) {
        this.countAll = countAll;
    }

    synchronized public void setCountRes(int countRes) {
        this.countRes = countRes;
    }

    @Override
    synchronized public String toString() {
        return "CountVO{" +
                "countAll=" + countAll +
                ", countRes=" + countRes +
                '}';
    }
}
