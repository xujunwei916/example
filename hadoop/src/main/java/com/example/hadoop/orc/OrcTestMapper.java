package com.example.hadoop.orc;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.orc.mapred.OrcStruct;

import java.io.IOException;

public class OrcTestMapper extends Mapper<NullWritable, OrcStruct, Text, Text> {
    private Text oKey = new Text();
    private Text oValue = new Text();

    public void map(NullWritable key, OrcStruct value, Context context) throws IOException, InterruptedException {
        //要知道OrcStruct存储的结构
        StringBuffer bf = new StringBuffer();
        if (value.getNumFields() == 3) {
            Text valAcount = (Text) value.getFieldValue(0);
            Text valDomain = (Text) value.getFieldValue(1);
            Text valPost = (Text) value.getFieldValue(2);
            bf.append(valAcount.toString()).append("|").append(valDomain.toString()).append("|").append(valPost.toString());
        }
        if (bf.length() > 0) oValue.set(bf.toString());
        else oValue.set("");
        oKey.set("");
        context.write(oKey, oValue);
    }
}
