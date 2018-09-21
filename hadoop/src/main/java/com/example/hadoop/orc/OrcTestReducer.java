package com.example.hadoop.orc;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.orc.mapred.OrcStruct;

import java.io.IOException;

public class OrcTestReducer extends Reducer<Text, Text, Text, Text> {
    //具体OrcStruct字段对应hadoop的定义参考https://orc.apache.org/docs/mapreduce.html


    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text val : values) {
            System.out.println(key.toString() + " = " + val.toString());
            context.write(key, val);
        }

    }
}
