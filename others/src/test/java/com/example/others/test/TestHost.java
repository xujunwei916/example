package com.example.others.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestHost {
    public static void main(String[] args) throws UnknownHostException {
       String aaa= InetAddress.getLocalHost().getCanonicalHostName();
        System.out.println(aaa);
    }
}
