package com.example.hive.udf;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * UDFJson.
 */
public class UDFIP extends UDF {

    public static IPLocateDatx ipLocateDatx = new IPLocateDatx() ;


    static {
        try {
            FileSystem fs = FileSystem.get(new Configuration());
            InputStream in=fs.open(new Path("/user/xujw/IP_trial_single_WGS84.datx"));
            ipLocateDatx.loadHdfsDat(in);
            in.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public ArrayList<Text> evaluate(String ip) {
        String result = ipLocateDatx.locateIp(ip);

        ArrayList<Text> texts = new ArrayList<>();

        String[] geo = ipLocateDatx.locateIp(ip).split("\\|", -1);
        if (geo.length != 3) {
            texts.add(new Text(IPLocateDatx.UNKNOWN));
            texts.add(new Text(IPLocateDatx.UNKNOWN));
            texts.add(new Text(IPLocateDatx.UNKNOWN));
        } else {

            texts.add(new Text(StringUtils.isEmpty(geo[0]) ? IPLocateDatx.UNKNOWN : geo[0]));
            texts.add(new Text(StringUtils.isEmpty(geo[1]) ? IPLocateDatx.UNKNOWN : geo[1]));
            texts.add(new Text(StringUtils.isEmpty(geo[2]) ? IPLocateDatx.UNKNOWN : geo[2]));
        }
        return texts;


    }


}

