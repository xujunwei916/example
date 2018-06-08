package com.example.others.ua;


import eu.bitwalker.useragentutils.UserAgent;

public class UserAgentUse {

    public static void main(String[] args) {
        String ua= "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36";
        UserAgent userAgent= UserAgent.parseUserAgentString(ua);
        System.out.println(userAgent.getBrowser());
        System.out.println(userAgent.getBrowserVersion());
        System.out.println(userAgent.getOperatingSystem().getGroup().getName());
        System.out.println(userAgent.getOperatingSystem().getName());
    }
}
