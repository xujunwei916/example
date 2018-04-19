package com.example.hadoop.common;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import java.io.IOException;

/**
 * 启动hadoop的一些辅助功能
 * @author JW
 */
public class JobUtils {

    public static String JOB_CLASS_PATH ="job.class.path";


    /**
     * 使用分布式缓存把文件添加到map和reduce的jvm的classpath
     * @param conf configuration
     * @param fs  文件系统
     * @throws IOException 分布式缓存失败
     */
    public static void addFileToClassPath(Configuration conf, FileSystem fs) throws IOException {
        Path jobClassPath = new Path(conf.get(JOB_CLASS_PATH, "/job_classpath"));
        if (fs.exists(jobClassPath)) {
            FileStatus[] jars = fs.listStatus(jobClassPath);
            if (!(jars == null || jars.length == 0)) {
                for (FileStatus fileStatus : jars) {
                    DistributedCache.addFileToClassPath(fileStatus.getPath(), conf, fs);
                }
            }
        }
    }

}
