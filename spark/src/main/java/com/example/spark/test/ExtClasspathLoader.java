package com.example.spark.test;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 根据properties中配置的路径把jar和配置文件加载到classpath中。
 *
 * @author jnbzwm
 */
public final class ExtClasspathLoader {


    public static void main(String[] args) {

        String filePath = "";
        Process processor = null;
        URL url =null;
        try {
            url = new URL(filePath);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
//       Thread.currentThread().getContextClassLoader().


    }

}