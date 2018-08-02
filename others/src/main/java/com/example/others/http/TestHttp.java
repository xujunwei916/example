package com.example.others.http;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class TestHttp {

    public static Gson GSON = new Gson();
    public static Type listType = new com.google.common.reflect.TypeToken<Map<String, Object>>() {
    }.getType();

    public static void main(String[] args) throws Exception {
        String dir = "D:\\test\\zhushou_2018_07_08\\opt\\data\\appNewLogs\\2018\\2018-07-08\\zhushou";
        String filenames []= new File(dir).list();
        String outputDir="d:\\test\\result_tmp";
        String errorDir ="d:\\test\\result_errer";
        for (String filename:filenames
             ) {
            System.out.println(filename);
            TestPost(dir+"\\"+filename,outputDir+"\\"+filename,errorDir+"\\"+filename);
        }





    }

    private static void TestPost(String filename,String output,String error) throws IOException, InterruptedException {
        ExecutorService POOL = Executors.newFixedThreadPool(50);

        List<String> lines = IOUtils.readLines(new FileReader(filename));
        LinkedBlockingQueue<String> result = new LinkedBlockingQueue<String>();
        LinkedBlockingQueue<String> errorResult = new LinkedBlockingQueue<String>();

        for (String line : lines) {
            String fields[] = line.split("\t", 3);
            String ip = fields[0];
            String data = fields[1];
            String timestamp = fields[2];
            RequestJSON requestJSON = new RequestJSON();
            Map<String, Object> client = GSON.fromJson(data, listType);
            requestJSON.setClient(client);
            requestJSON.setAppname("shouzhu");
            requestJSON.setIp(ip);

            requestJSON.setScore(5);
            Map<String, Object> header = (Map<String, Object>) client.get("header");
            requestJSON.setTaskid("00001");
            requestJSON.setPassid((String) header.get("uid"));
            String json = GSON.toJson(requestJSON);

//            System.out.println(json);
            POOL.submit(new Runnable() {
                @Override
                public void run() {
                    String response = HttpRequest.sendJSON("http://www.xqprevention.com/cheat_api/realtime_cheat", json);
                    if(StringUtils.isEmpty(response)){
                        errorResult.add(json);
                    }
                    result.add(response);
                }
            });

        }

//        Thread.sleep(30000);
        POOL.shutdown();
        while (true) {
            if (POOL.isTerminated()) {
                System.out.println("结束了！");
                break;
            }
            Thread.sleep(200);
        }
        FileWriter writer = new FileWriter(output);
        IOUtils.writeLines(result,null,writer );
        writer.close();

        FileWriter errorwriter = new FileWriter(error);
        IOUtils.writeLines(errorResult,null,errorwriter );
        errorwriter.close();
    }
}
