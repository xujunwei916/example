package com.example.hive.udf;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * UDFJson.
 */
public class UDFJsonArray extends UDF {
    private ObjectMapper mapper = new ObjectMapper();

    public ArrayList<Text> evaluate(String jsonString) {
        List json = null;
        try {
            json = mapper.readValue(jsonString, List.class);

            if (json != null && !json.isEmpty()) {
                ArrayList<Text> texts = new ArrayList<>();
                for (Object o : json) {
                    if (o instanceof Map || o instanceof List) {
                        texts.add(new Text(mapper.writeValueAsString(o)));
                    } else {
                        texts.add(new Text(o.toString()));
                    }
                }

                return texts;
            }
        } catch (Exception e) {
            return null;
        }
        return null;

    }


}

