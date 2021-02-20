package com.example.others.saas;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class Saas38 {

  public static void main(String[] args) throws Exception {

    String input = "D:\\jira\\saastest\\38\\38_linear_path.csv";

    String output = "D:\\jira\\saastest\\38\\38_linear_path_result.csv";
    List<String> lines = IOUtils.readLines(new FileReader(input));

    Map<String,String> map = new HashMap<>();
    map.put("5660147","小米Xiaomi_OTT_Opening Video_15s-QIN130_沁园2020吴尊纯净可见片_5月京东版_15s_OV_国语-CC80262MP1053233572184");
    map.put("5660144","小米Xiaomi_Pre-roll_15s-QIN130_沁园2020吴尊纯净可见片_5月京东版_15s_OV_国语-CC80262MP1053233532184");
    map.put("5660141","搜狐视频Sohu_Pre-roll_15s-QIN130_沁园2020吴尊纯净可见片_5月京东版_15s_OV_国语-CC80262MP10532sohu532184");
    map.put("5660138","芒果视频Mango_Pre-roll_15s-QIN130_沁园2020吴尊纯净可见片_5月京东版_15s_OV_国语-CC80262MP10532publisher20532184");
    map.put("5660135","爱奇艺视频iQIYI_Pre-roll_15s-QIN130_沁园2020吴尊纯净可见片_5月京东版_15s_OV_国语-CC80262MP105329532184");


    List<String> result = new ArrayList<>();

    for (String line:lines    ) {
      String [] fields = line.split(",");
      String [] path = fields[0].split("\\|");
      String [] path2 = new String [path.length];
      for (int i = 0; i < path.length; i++) {
        String ad =path[i].substring(2);
        String adName = map.get(ad);
        path2[i] = path[i].substring(0,2)+adName;
      }
      fields[0] = StringUtils.join(path2,"|");
      result.add(StringUtils.join(fields,","));
    }

    FileWriter writer = new FileWriter(output);
    IOUtils.writeLines(result,null,writer);
    writer.close();


  }

}
