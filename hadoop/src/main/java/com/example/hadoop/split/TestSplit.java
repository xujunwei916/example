package com.example.hadoop.split;

import com.example.hadoop.common.JobUtils;
import com.example.hadoop.orc.OrcTestDriver;
import com.example.hadoop.orc.OrcTestMapper;
import com.example.hadoop.orc.OrcTestReducer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.orc.mapreduce.OrcInputFormat;

import java.util.List;

public class TestSplit {


    public static void main(String[] args)  throws Exception {
        Configuration conf = new Configuration();
        conf.setInt("mapreduce.input.fileinputformat.split.minsize",150*1024*1024);

        FileSystem fs = FileSystem.get(conf);
        Job job = Job.getInstance(conf, "ORCSample");
        job.setJarByClass(OrcTestDriver.class);
        JobUtils.addFileToClassPath(job.getConfiguration(), fs);
        job.setMapperClass(OrcTestMapper.class);
        job.setReducerClass(OrcTestReducer.class);
        //map类型设置
        job.setInputFormatClass(TextInputFormat.class);
        job.setMapOutputKeyClass(Text.class);//优先于setOutputKeyClass生效于map
        job.setMapOutputValueClass(Text.class);
        //reduce类型设置
        job.setNumReduceTasks(1);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        OrcInputFormat.addInputPath(job, new Path("/tmp/00.txt.gz"));
        FileOutputFormat.setOutputPath(job, new Path("/tmp/001"));
        List<InputSplit> splits = new TextInputFormat().getSplits(job);
        for (Object o :splits
             ) {
            System.out.println(o);

        }
    }
}
