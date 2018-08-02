package com.example.others.ua;


import eu.bitwalker.useragentutils.UserAgent;

public class UserAgentUse {

    public static void main(String[] args) {
        String ua= "Mozilla/5.0 (Linux; Android 6.0; HUAWEI CRR-UL00 Build/HUAWEICRR-UL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/55.0.2883.91 Mobile Safari/537.36";
        UserAgent userAgent= UserAgent.parseUserAgentString(ua);
        System.out.println(userAgent.getBrowser());
        System.out.println(userAgent.getOperatingSystem().getName());
        System.out.println(userAgent.getOperatingSystem().getDeviceType());
    }
}
