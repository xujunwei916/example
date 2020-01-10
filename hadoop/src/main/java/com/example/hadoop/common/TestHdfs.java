package com.example.hadoop.common;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.ContentSummary;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsShell;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.yarn.client.cli.LogsCLI;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TestHdfs {
    public static void main(String[] args) throws Exception {
        FileSystem fs = FileSystem.get(new Configuration());
//        FileStatus[] files = fs.listStatus(new Path("/ods"));

        ContentSummary contentSummary = fs.getContentSummary(new Path("/ods"));
        System.out.println(
                contentSummary.toString(false)
        );

//        contentSummary.getFileCount()

//        RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator =  fs.listFiles(new Path("/ods"),true);

//        for (LocatedFileStatus file : locatedFileStatusRemoteIterator.) {
////            System.out.println(file.getPath());
//        }

//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(100);
//        FsShell.main(new String[]{"-count","/ods"});
//        FileSystem fs2 = FileSystem.get(new Configuration());
//        FileStatus[] files2 = fs2.listStatus(new Path("/sdddd"));
//        for (FileStatus file : files2) {
//            System.out.println(file.getPath());
//        }


//        FSDataOutputStream fsDataOutputStream = fs.create(new Path("dddd"));

//        LogsCLI.main(new String[]{"-applicationId","application_1534917772850_0001"});


//        fs.mkdirs( new Path("/tmp/xvcvx dfsadad"));

    }
}
