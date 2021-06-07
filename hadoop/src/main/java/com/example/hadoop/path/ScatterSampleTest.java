package com.example.hadoop.path;
 
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
 
 
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.parallel.InputStreamSupplier;
import org.apache.commons.io.input.NullInputStream;
import org.junit.Test;
 
 
public class ScatterSampleTest {
 
 
    @Test
    public void testSample() throws Exception {
        long begin = System.currentTimeMillis();
        final File result = new File("d:/test/zip/result/testMulti.zip");
        createZipFile("d:/test/zip/source", result);
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - begin) + " ms");
    }
 
 
    class CustomInputStreamSupplier implements InputStreamSupplier {
        private File currentFile;
 
 
        public CustomInputStreamSupplier(File currentFile) {
            this.currentFile = currentFile;
        }
 
 
        @Override
        public InputStream get() {
            try {
                // InputStreamSupplier api says:
                // 返回值：输入流。永远不能为Null,但可以是一个空的流
                return currentFile.isDirectory() ? new NullInputStream(0) : new FileInputStream(currentFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
 
 
    private void addEntry(String entryName, File currentFile, ScatterSample scatterSample) throws IOException {
        ZipArchiveEntry archiveEntry = new ZipArchiveEntry(entryName);
        archiveEntry.setMethod(ZipEntry.DEFLATED);
        final InputStreamSupplier supp = new CustomInputStreamSupplier(currentFile);
        scatterSample.addEntry(archiveEntry, supp);
    }
 
 
    private void compressCurrentDirectory(File dir, ScatterSample scatterSample) throws IOException {
        if (dir == null) {
            throw new IOException("源路径不能为空！");
        }
        String relativePath = "";
        if (dir.isFile()) {
            relativePath = dir.getName();
            addEntry(relativePath, dir, scatterSample);
            return;
        }
 
 
        // 空文件夹
        if (dir.listFiles().length == 0) {
            relativePath = dir.getAbsolutePath().replace(scatterSample.getRootPath(), "");
            addEntry(relativePath + File.separator, dir, scatterSample);
            return;
        }
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                compressCurrentDirectory(f, scatterSample);
            } else {
                relativePath = f.getParent().replace(scatterSample.getRootPath(), "");
                addEntry(relativePath + File.separator + f.getName(), f, scatterSample);
            }
        }
    }
 
 
    private void createZipFile(final String rootPath, final File result) throws Exception {
        File dstFolder = new File(result.getParent());
        if (!dstFolder.isDirectory()) {
            dstFolder.mkdirs();
        }
        File rootDir = new File(rootPath);
        final ScatterSample scatterSample = new ScatterSample(rootDir.getAbsolutePath());
        compressCurrentDirectory(rootDir, scatterSample);
        final ZipArchiveOutputStream zipArchiveOutputStream = new ZipArchiveOutputStream(result);
        scatterSample.writeTo(zipArchiveOutputStream);
        zipArchiveOutputStream.close();
    }
}

