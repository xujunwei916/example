package com.example.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.net.URLDecoder;


public class UDFUrlEncode extends UDF {


    public Text evaluate(String url, String code) {


        try{
            String s = URLDecoder.decode(url,code);
            return new Text(s);

        }catch (Exception e){
            return null;
        }
    }
}
