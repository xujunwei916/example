package com.example.others.uri;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class TestUri {
    public static void main(String[] args) throws Exception {
        String path = "https://www.aaaa.com/s/sdddd.txt";
        URI uri = new URI(path);
        System.out.println(uri.toURL());
        System.out.println(uri.getScheme());
        System.out.println(uri.getRawPath());


    }
}
