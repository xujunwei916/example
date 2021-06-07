package com.example.hadoop.path;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class TestCSV {

  public static void main(String[] args) throws Exception {

    FileSystem fs =FileSystem.get(new Configuration());

    OutputStream outputStream = fs.create(new Path("/user/Hypers/test.zip"));

    ZipOutputStream zipOut = new ZipOutputStream(
        outputStream);
    zipOut.putNextEntry(new ZipEntry("sss.csv"));

    CSVWriter writer = new CSVWriter(
        new BufferedWriter(new OutputStreamWriter(zipOut, "utf-8"))
        );
    writer.writeNext(new String[]{"aaa","bbbb"});
    for (int i=0;i<1000000;i++) {
      writer.writeNext(new String[]{"i",i+""});
    }

    writer.flush();
    zipOut.closeEntry();
    zipOut.close();
    writer.close();
    outputStream.close();

  }
}
