package com.example.flume.filter;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;

public class TestUtils {

    public static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static long getTimeStampByStr(String time) {
        try {
            return sdf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public static Map<String, String> parseQueryString(String value) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isEmpty(value)) {
            return map;
        } else {
            String fields[] = value.split("&", -1);
            for (String field : fields) {
                if (!StringUtils.isEmpty(field)) {
                    String kv[] = field.split("=", 2);
                    if (kv != null && kv.length == 2 && !StringUtils.isEmpty(kv[0])) {
                        map.put(kv[0].trim().toLowerCase(), StringUtils.isEmpty(kv[1]) ? "" : kv[1].trim());
                    }
                }
            }
        }

        return map;

    }
}
