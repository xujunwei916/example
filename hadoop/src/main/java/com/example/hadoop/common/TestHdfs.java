package com.example.hadoop.common;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.yarn.client.cli.LogsCLI;

import java.io.IOException;

public class TestHdfs {
    public static void main(String[] args) throws Exception {
        FileSystem fs = FileSystem.get(new Configuration());
        FileStatus [] files= fs.listStatus(new Path("/"));
        for (FileStatus file: files             ) {
            System.out.println(file.getPath());
        }

//        LogsCLI.main(new String[]{"-applicationId","application_1534917772850_0001"});

    }
}
