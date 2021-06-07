package com.example.others.csv;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCsvTest {

  public static void main(String[] args) throws Exception {

    OutputStream outputStream = new FileOutputStream("D:\\test\\zip\\result.zip");

    ZipOutputStream zipOut = new ZipOutputStream(outputStream
        );

    zipOut.putNextEntry(new ZipEntry("sss.csv"));

    CSVWriter writer = new CSVWriter(new BufferedWriter(
        new OutputStreamWriter(zipOut, "utf-8")
    ),',', CSVWriter.NO_QUOTE_CHARACTER
    );
    writer.writeNext(new String[]{"aaa","bbbb"});
    for (int i=0;i<1000000;i++) {
      writer.writeNext(new String[]{"i",i+""});
    }

    writer.flush();
    zipOut.closeEntry();
    zipOut.close();
    writer.close();


  }
}
