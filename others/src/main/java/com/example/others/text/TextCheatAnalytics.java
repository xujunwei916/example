package com.example.others.text;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextCheatAnalytics {

    public static void main(String[] args) throws Exception {
        String path = "D:\\test\\cheat\\cheat";
        File filePath = new File(path);
        List<File> fileList = (List<File>) FileUtils.listFiles(filePath, null, true);
//        String files [] = filePath.

        Pattern pattern = Pattern.compile("^start = (\\d+) , .*, end = (\\d+).*product='([0-9a-zA-Z]+)'.*$");

        Map<String, List<Result>> result = new HashMap<>();

        SimpleDateFormat SSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<String> results = new ArrayList<>();
        Map<String,Long> dateResult = new HashMap<>();
        Map<String,Long> dateHourResult = new HashMap<>();
        Map<String,Map<String,Long>> dateAppResult = new HashMap<>();

        for (File file : fileList) {

            List<Result> tmpResult = new ArrayList<>();

            List<String> lines = IOUtils.readLines(new FileReader(file));

            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    long start = Long.valueOf(matcher.group(1));
                    long end = Long.valueOf(matcher.group(2));
                    String appName = matcher.group(3);
                    String date = SSS.format(new Date(start));
                    Result r = new Result(date.substring(0, 10), date, end - start, appName, date.substring(11, 13), date.substring(14, 16));
                    tmpResult.add(r);
                    results.add(r.toLine());
                    Long tmpCount = dateResult.get(date.substring(0,10));
                    if(tmpCount==null){
                        tmpCount=0L;
                    }
                    tmpCount++;
                    dateResult.put(date.substring(0,10),tmpCount);

                    Long tmpCount1 = dateHourResult.get(date.substring(0,13));
                    if(tmpCount1==null){
                        tmpCount1=0L;
                    }
                    tmpCount1++;
                    dateHourResult.put(date.substring(0,13),tmpCount1);

                }
            }
            result.put(file.getAbsolutePath(),tmpResult);
        }


//        FileWriter writer = new FileWriter(new File("D://test//cheat//result.txt"));
//        IOUtils.writeLines(results,null,writer);
//        writer.close();


        System.out.println(dateResult);
        System.out.println(dateHourResult);

        for (Map.Entry<String,List<Result>> entry:result.entrySet()) {
            System.out.println(entry.getKey()+","+ entry.getValue().size()+","+entry.getValue().get(0));

        }
    }

}

class Result {
    private String date;

    private String time;

    private long costTime;

    private String appname;

    private String hr;

    private String mi;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getMi() {
        return mi;
    }

    public void setMi(String mi) {
        this.mi = mi;
    }

    public Result() {
    }

    public Result(String date, String time, long costTime, String appname, String hr, String mi) {
        this.date = date;
        this.time = time;
        this.costTime = costTime;
        this.appname = appname;
        this.hr = hr;
        this.mi = mi;
    }

    @Override
    public String toString() {
        return "Result{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", costTime='" + costTime + '\'' +
                ", appname='" + appname + '\'' +
                ", hr='" + hr + '\'' +
                ", mi='" + mi + '\'' +
                '}';
    }


    public String toLine() {
        return date + "," + time + "," + costTime + "," + appname + "," + hr + "," + mi;
    }

}
