package com.example.hadoop.orc;

import com.example.hadoop.common.JobUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobPriority;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.orc.mapreduce.OrcInputFormat;


public class OrcTestDriver implements Tool {
    private Configuration conf;

    @Override
    public Configuration getConf() {
        return conf;
    }

    @Override
    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public static void main(String args[]) throws Exception {
        int res = ToolRunner.run(new Configuration(), new OrcTestDriver(), args);
        System.exit(res);
    }

    @Override
    public int run(String[] args) throws Exception {
        conf.set("hive.llap.io.cache.orc.size","175953100");
        //要设置结构，否则reduce会提示输入空值
        conf.set("orc.mapred.output.schema", "struct<account:string,domain:string,post:string>");
        FileSystem fs = FileSystem.get(conf);
        Job job = Job.getInstance(conf, "ORCSample");
        job.setJarByClass(OrcTestDriver.class);
        JobUtils.addFileToClassPath(job.getConfiguration(), fs);
        job.setMapperClass(OrcTestMapper.class);
        job.setReducerClass(OrcTestReducer.class);
        //map类型设置
        job.setInputFormatClass(OrcInputFormat.class);
        job.setMapOutputKeyClass(Text.class);//优先于setOutputKeyClass生效于map
        job.setMapOutputValueClass(Text.class);
        //reduce类型设置
        job.setNumReduceTasks(1);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //job.setOutputKeyClass(Text.class);//对map和reduce输出都生效
        //job.setOutputValueClass(Text.class);
        //输入输出路径
        job.setPriority(JobPriority.LOW);
        OrcInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        return job.waitForCompletion(true) ? 0 : 1;
    }
}
