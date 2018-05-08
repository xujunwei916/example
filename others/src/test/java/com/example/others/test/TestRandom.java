package com.example.others.test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class TestRandom {
    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        for (int i = 0; i < 1000 ; i++) {
            System.out.println(Math.random()+"");
        }
        String url = "https://127.0.0.1/developerworks/cn/java/j-lo-jsouphtml/";
        System.out.println(new URI(url).getHost());
    }
}
