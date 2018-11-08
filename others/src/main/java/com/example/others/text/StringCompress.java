package com.example.others.text;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class StringCompress {
    public static final byte[] gzipCompress(String paramString) {
        if (paramString == null)
            return null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        GZIPOutputStream gzipOutputStream = null;
        byte[] arrayOfByte;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(paramString.getBytes());
            gzipOutputStream.close();
            arrayOfByte = byteArrayOutputStream.toByteArray();
        } catch (IOException localIOException5) {
            arrayOfByte = null;
        } finally {
            if (gzipOutputStream != null)
                try {
                    gzipOutputStream.close();
                } catch (IOException localIOException6) {
                }
            if (byteArrayOutputStream != null)
                try {
                    byteArrayOutputStream.close();
                } catch (IOException localIOException7) {
                }
        }
        return arrayOfByte;
    }


    public static final byte[] gzipDeflaterCompress(String paramString) {
        if (paramString == null)
            return null;
        Deflater compresser = null;
        byte[] arrayOfByte = null;
        byte[] tmp = new byte[500];
        try {
            compresser = new Deflater();
            compresser.setInput(paramString.getBytes());
            compresser.finish();
            int size =compresser.deflate(tmp);
            arrayOfByte = new byte[size];
            System.arraycopy(tmp,0,arrayOfByte,0,size);

        } catch (Exception localIOException5) {
            arrayOfByte = null;
        } finally {
            if (compresser != null) {
                compresser.finish();
            }
        }
        return arrayOfByte;
    }

    public static final byte[] zipCompress(String paramString) {
        if (paramString == null)
            return null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ZipOutputStream zipOutputStream = null;
        byte[] arrayOfByte;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
            zipOutputStream.putNextEntry(new ZipEntry("0"));
            zipOutputStream.write(paramString.getBytes());
            zipOutputStream.closeEntry();
            arrayOfByte = byteArrayOutputStream.toByteArray();
        } catch (IOException localIOException5) {
            arrayOfByte = null;
        } finally {
            if (zipOutputStream != null)
                try {
                    zipOutputStream.close();
                } catch (IOException localIOException6) {
                }
            if (byteArrayOutputStream != null)
                try {
                    byteArrayOutputStream.close();
                } catch (IOException localIOException7) {
                }
        }
        return arrayOfByte;
    }


    @SuppressWarnings("unused")
    public static final String decompress(byte[] paramArrayOfByte) {
        if (paramArrayOfByte == null)
            return null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ZipInputStream zipInputStream = null;
        String str;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
            zipInputStream = new ZipInputStream(byteArrayInputStream);
            ZipEntry localZipEntry = zipInputStream.getNextEntry();
            byte[] arrayOfByte = new byte[1024];
            int i = -1;
            while ((i = zipInputStream.read(arrayOfByte)) != -1)
                byteArrayOutputStream.write(arrayOfByte, 0, i);
            str = byteArrayOutputStream.toString();
        } catch (IOException localIOException7) {
            str = null;
        } finally {
            if (zipInputStream != null)
                try {
                    zipInputStream.close();
                } catch (IOException localIOException8) {
                }
            if (byteArrayInputStream != null)
                try {
                    byteArrayInputStream.close();
                } catch (IOException localIOException9) {
                }
            if (byteArrayOutputStream != null)
                try {
                    byteArrayOutputStream.close();
                } catch (IOException localIOException10) {
                }
        }
        return str;
    }
}    