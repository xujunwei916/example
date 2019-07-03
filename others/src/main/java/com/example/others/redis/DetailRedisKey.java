package com.example.others.redis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Pattern;

public class DetailRedisKey {

    public static void main(String[] args) throws Exception {

        Pattern pattern = Pattern.compile("^[0-9A-F]{28}$");
        BufferedReader reader = new BufferedReader(new FileReader("D://test/redis/redis.key"));

        BufferedWriter uidWriter = new BufferedWriter(new FileWriter("D://test/redis/uid.key"));
        BufferedWriter othersWriter = new BufferedWriter(new FileWriter("D://test/redis/others.key"));


        String line = null;

        int countUid = 0;
        int countOther = 0;
        while ((line = reader.readLine()) != null) {
            if (pattern.matcher(line).matches()) {
                uidWriter.write(line);
                uidWriter.newLine();
                countUid++;
            } else {
                othersWriter.write(line);
                othersWriter.newLine();
                countOther++;
            }
        }
        uidWriter.close();
        othersWriter.close();

        System.out.println("countUid = " + countUid);
        System.out.println("countOther = " + countOther);


    }

}
