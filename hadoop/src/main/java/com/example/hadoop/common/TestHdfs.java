package com.example.hadoop.common;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class TestHdfs {
    public static void main(String[] args) throws IOException {
        FileSystem fs = FileSystem.get(new Configuration());
        FileStatus [] files= fs.listStatus(new Path("/"));
        for (FileStatus file: files             ) {
            System.out.println(file.getPath());
        }


    }
}
