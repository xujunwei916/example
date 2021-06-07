package com.example.hadoop.path;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.apache.commons.compress.archivers.zip.ParallelScatterZipCreator;
import org.apache.commons.compress.archivers.zip.ScatterZipOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntryRequest;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.parallel.InputStreamSupplier;

public class ScatterSample {

  private String rootPath;
  ParallelScatterZipCreator scatterZipCreator = new ParallelScatterZipCreator();


  ScatterZipOutputStream dirs = ScatterZipOutputStream.fileBased(
      File.createTempFile("whatever-preffix", ".whatever"));

  public ScatterSample(String rootPath) throws IOException {
    this.rootPath  = rootPath;
  }

  public void addEntry(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier streamSupplier)
      throws IOException {
    if (zipArchiveEntry.isDirectory() && !zipArchiveEntry.isUnixSymlink()) {
      dirs.addArchiveEntry(
          ZipArchiveEntryRequest.createZipArchiveEntryRequest(zipArchiveEntry, streamSupplier));
    } else {
      scatterZipCreator.addArchiveEntry(zipArchiveEntry, streamSupplier);
    }
  }

  public void writeTo(ZipArchiveOutputStream zipArchiveOutputStream)
      throws IOException, ExecutionException, InterruptedException {
    dirs.writeTo(zipArchiveOutputStream);
    dirs.close();
    scatterZipCreator.writeTo(zipArchiveOutputStream);
  }

  public String getRootPath() {
    return rootPath;
  }


  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

}