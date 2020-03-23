package com.example.others.text;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestCreateTxt {
    public static void main(String[] args) throws Exception {
        int len = 10000;

        List<String> lines = new ArrayList<>(len);

        List<String> types = Arrays.asList(new String[]{"int", "string", "string", "string", "int", "string", "date"});


        for (int i = 0; i < len; i++) {
            lines.add(createLine(types));
        }

        FileWriter writer = new FileWriter("d:\\test\\test2.csv");

        IOUtils.writeLines(lines, null, writer);

        writer.close();


    }


    public static String createLine(List<String> types) {
        List<String> result = new ArrayList<>();
        for (String ty : types) {
            switch (ty) {

                case "int":
                    result.add(String.valueOf(new Random().nextInt(100000)));
                    break;
                case "string":
                    result.add("test");
                    break;
                case "date":
                    result.add("2019-03-09");
                    break;
            }
        }
        return StringUtils.join(result, ",");
    }
}
