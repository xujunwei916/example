package com.example.others.object;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Kxdislpay {
    public static void main(String[] args) {
        //不用科学计数法显示Double类型和Long类型
        LongNoKX();
        DoubleNoKX();
    }

    public static void LongNoKX() {
        long ll = 1234567845134321l;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        System.out.println("" + nf.format(ll));
    }

    public static void DoubleNoKX() {
        double dd = 234536789.234567890;
        BigDecimal bd = new BigDecimal(dd);
        //BigDecimal result=bd.setScale(3,  BigDecimal.ROUND_HALF_DOWN);
        System.out.println(bd.toString());
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        System.out.println("" + nf.format(dd));
        System.out.println(dd);
        System.out.println(String.format("%f",bd));
    }
}
