package com.example.others.text;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TestDouble {


    public static void main(String[] args) {
        double d=1000000000.222;
        System.out.println(d+"");
        System.out.println(big(d));
        System.out.println(formatNum(d));
    }
    private static String big(double d) {
        NumberFormat nf = NumberFormat.getInstance();
        // 是否以逗号隔开, 默认true以逗号隔开,如[123,456,789.128]
        nf.setGroupingUsed(false);
        // 结果未做任何处理
        return nf.format(d);
    }


    public static String formatNum(double value) {
        String retValue = null;
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(0);
        df.setMaximumFractionDigits(8);
        retValue = df.format(value);
        retValue = retValue.replaceAll(",", "");
        return retValue;
    }

    }
