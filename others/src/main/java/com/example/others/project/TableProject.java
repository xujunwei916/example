package com.example.others.project;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TableProject {

    Map<String, String> projectMap = new LinkedHashMap<>();

    public TableProject() {

        Map<String, String> tmp = new LinkedHashMap<>();
        tmp.put("product_key", "product_rule");
        tmp.put("xqlm2", "xqlm2|xqlm");
        tmp.put("xqly", "xqly");
        tmp.put("hbtt", "hbtt");
        tmp.put("xqtt", "xqtt");
        tmp.put("tianqiwang", "tianqiwang|tianqi15|tqyb");
        tmp.put("zhushou", "zhushou|shouzhu");
        tmp.put("browser_app", "browser_app|m_browser_app|jsbrowser2");
        tmp.put("mbsearch", "mbsearch");
        tmp.put("uncheat", "uncheat");
        tmp.put("loginsdk", "loginsdk");
        tmp.put("calendar", "calendar");
        tmp.put("gsq", "gsq");
        tmp.put("safe", "safe|product_click|dmdt|common_extool|helper|process_chain|comp_monitor|highjack|reverse_intercept|summary_data|minipage");
        tmp.put("browser", "browser");
        tmp.put("pinyin", "pinyin");
        tmp.put("pic", "pic");
        tmp.put("haozip", "haozip");
        tmp.put("ruanjian", "ruanjian");
        tmp.put("daohang", "daohang|navigation");
        tmp.put("jifen", "jifen");
        tmp.put("interactive", "interactive|commercialization");
        tmp.put("shoujilm", "shoujilm");
        for (Map.Entry<String, String> entry : tmp.entrySet()) {
            String keys[] = entry.getValue().split("\\|");
            for (String key : keys) {
                projectMap.put("_" + key + "_", entry.getKey());
            }
        }
    }


    private String projectFromName(String database, String tableName, String defaultProject) {
        if (tableName == null) {
            return null;
        }

        int index = Integer.MAX_VALUE;
        String projectName = null;
        Set<String> all = new HashSet<>();

        for (Map.Entry<String, String> entry : projectMap.entrySet()) {
            int find = tableName.indexOf(entry.getKey());
            if (find == -1) {
                continue;
            } else {
                all.add(entry.getValue());
                if (projectName == null) {
                    projectName = entry.getValue();
                    index = find;
                } else {
                    if (index > find || index == find && entry.getValue().compareTo(projectName) > 0) {
                        projectName = entry.getValue();
                        index = find;
                    }
                }
            }
        }
        if (all.contains("safe")) {
            projectName = "safe";
        }
        if (all.size() > 1) {
            System.out.println(tableName + "\t" + projectName + "\t" + all);
        }
        if (projectName == null) {
            return defaultProject;
        } else {
            return projectName;
        }
    }


    public static void main(String[] args) throws Exception {
        TableProject tableProject = new TableProject();


        List<String> lines = IOUtils.readLines(new FileReader("D:\\tmp\\test\\table.txt"));


        for (String line : lines) {
            tableProject.projectFromName("aaaa", line, "others");

        }

//        tableProject.projectFromName("aaaa","dm_channel_inst_log_browser_pull_safe_pre","others");


    }
}
