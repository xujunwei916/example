package com.example.hive;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hive.ql.io.orc.OrcNewOutputFormat;
import org.apache.hadoop.hive.ql.io.orc.OrcNewOutputFormat2;

import org.apache.hadoop.hive.ql.io.orc.OrcSerde;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.TaskAttemptID;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.task.TaskAttemptContextImpl;

import java.io.IOException;
import java.util.List;

public class TestORC {

    public static void main(String[] args) throws Exception {

//        FileOutputFormat

//        OrcOutputFormat.

//        org.apache.hadoop.hive.ql.io.orc.OrcOutputFormat

//        OrcNewOutputFormat2 orcNewOutputFormat2 = new OrcNewOutputFormat2();
//
//        TaskAttemptContext context = TaskAttemptContext
        OrcNewOutputFormat outputFormat =  new OrcNewOutputFormat();
        FileSystem fs = FileSystem.get(new Configuration());
        TaskAttemptContextImpl taskAttemptContext = new TaskAttemptContextImpl(fs.getConf(),new TaskAttemptID());
        RecordWriter writer = outputFormat.getRecordWriter(taskAttemptContext);
        OrcSerde orcSerde = new OrcSerde();
        List orcRecord;

//        Writable row;
//        writer.write();




    }
}


