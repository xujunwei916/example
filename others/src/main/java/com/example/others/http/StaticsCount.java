package com.example.others.http;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticsCount {

    public static final Gson GSON = new Gson();

    public static void main(String[] args) throws Exception {


        String tmpDir = "d:\\test\\result_tmp";
        String filenames[] = new File(tmpDir).list();
        long count = 0;
        long errorCount = 0;
        Map<Integer, Long> cheatCount = new HashMap<>();
        int errorRequest =0;

        for (String filename : filenames) {
//            System.out.println(filename);

            List<String> lines = IOUtils.readLines(new FileReader(tmpDir + "\\" + filename));
            for (String line : lines) {
                if(StringUtils.isEmpty(line)){
                    errorRequest++;
                    continue;
                }
                RealTimeResponse realTimeResponse = GSON.fromJson(line, RealTimeResponse.class);
                count++;
//                System.out.println(line);
                if (realTimeResponse.getGrant().intValue() == 1) {
                    errorCount++;
                    for (Integer cheatType : realTimeResponse.getCheatType()) {

                        Long tmp = cheatCount.get(cheatType);
                        if (tmp == null) {
                            tmp = 0L;
                        }
                        tmp++;
                        cheatCount.put(cheatType, tmp);
                    }
                }

            }
        }

        System.out.println("count = "+count);
        System.out.println("ERROR COUNT = "+errorCount);
        System.out.println("ERROR REQUEST = "+errorRequest);
        System.out.println("CHEAT COUNT = "+ cheatCount);


    }
}
