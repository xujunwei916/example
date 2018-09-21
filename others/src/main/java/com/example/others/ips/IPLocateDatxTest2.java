package com.example.others.ips;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class IPLocateDatxTest2 {
    private byte[] data;

    private int indexSize;
    private static Pattern pattern = Pattern.compile("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$");


    public void loadDat(String fileName) throws Exception {
        Path path = Paths.get(fileName);
        data = Files.readAllBytes(path);

        indexSize = (int) (IPdataConvert.byte2long(data[0], data[1], data[2], data[3]));
    }


    public void writerFile(String file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            long minip = 0;

            for (int i = 0; i < indexSize; i++) {

                int pos = i * 9 + 4;

                long maxip = IPdataConvert.byte2long(data[pos], data[pos + 1], data[pos + 2], data[pos + 3]);

                int addr = (int) (IPdataConvert.byte2long(data[pos + 4], data[pos + 5], data[pos + 6]));
                int len = (int) (IPdataConvert.byte2long(data[pos + 7], data[pos + 8]));

                byte[] resultByte = new byte[len];
                System.arraycopy(data, indexSize * 9 + 4 + addr, resultByte, 0, len);
                writer.write(StringUtils.join(new Object[]{minip, maxip, new String(resultByte, Charset.forName("utf8"))}, ","));
                writer.newLine();
                minip = maxip + 1;

            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void comp(String file, String fileComp) throws Exception {


        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileComp));

        String line = null;
        String minIp = "0";
        String maxIp = "0";
        String country = null;

        while ((line = reader.readLine()) != null) {


            String fields[] = line.split(",", 5);
            String country_tmp = fields[2];

            if (country == null) {
                minIp = fields[0];
                maxIp = fields[1];
                country = country_tmp;
            } else {

                if (country.equals(country_tmp) && !country_tmp.equals("中国")) {
                    maxIp = fields[1];
                } else {
                    if (country_tmp.equals("中国")) {
                        writer.write(StringUtils.join(new String[]{minIp, maxIp, country, "", ""}, ","));
                        writer.newLine();
                        writer.write(line);
                        writer.newLine();
                        country = null;
                    } else {
                        writer.write(StringUtils.join(new String[]{minIp, maxIp, country, "", ""}, ","));
                        writer.newLine();
                        minIp = fields[0];
                        maxIp = fields[1];
                        country = country_tmp;
                    }

                }


            }


        }
        reader.close();
        writer.close();

    }

    public static void compare(String file1, String file2) throws Exception {

        BufferedReader reader1 = new BufferedReader(new FileReader(file1));

        BufferedReader reader2 = new BufferedReader(new FileReader(file2));

        String line1 = null;
        String line2 = null;
        int count = 0;
        while ((line1 = reader1.readLine()) != null && (line2 = reader2.readLine()) != null) {

            count++;
            String fields1[] = line1.split(",");
            String fields2[] = line2.split(",");
            if (!fields1[1].equals(fields2[1])) {

                System.out.println(count);
                break;
            }

        }


    }

    public static void comp2(String file, String fileComp) throws Exception {


        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileComp));

        String line = null;
        String minIp = "0";
        String maxIp = "0";
        String country = null;

        int count=0;
        while ((line = reader.readLine()) != null) {

            count++;

            String fields[] = line.split(",", -1);
            if (fields.length<8){
                System.out.println(count);
                System.out.println(line);
            }
            String country_tmp = "中国".equals(fields[5]) ? fields[5] + "," + fields[6] + "," + fields[7] : fields[5] + ",,";

            if (country == null) {
                minIp = fields[0];
                maxIp = fields[1];
                country = country_tmp;
            } else {

                if (country.equals(country_tmp)) {
                    maxIp = fields[1];
                } else {
                    writer.write(StringUtils.join(new String[]{minIp, maxIp, country}, ","));
                    writer.newLine();
                    minIp = fields[0];
                    maxIp = fields[1];
                    country = country_tmp;
                }

            }
        }
        writer.write(StringUtils.join(new String[]{minIp, maxIp, country}, ","));
        writer.newLine();
        reader.close();
        writer.close();

    }


    public static void main(String[] args) throws Exception {
//        IPLocateDatxTest2 ipLocate = new IPLocateDatxTest2();
//        ipLocate.loadDat("D://test//IP_trial_2018M06_single_WGS84.datx");
//        ipLocate.writerFile("D://test//test.txt");
//        ipLocate.comp("F:\\知识\\IP_basic_2018W33_single_WGS84_simple_dat\\test_owner.txt","F:\\知识\\IP_basic_2018W33_single_WGS84_simple_dat\\test_owner_2.txt");
//        new IPLocate().loadDat("D://test//IP_trial_2018M06_single_WGS84.dat");
//      long result =   new IPLocate().ipToLong("1.204.54.213");
//        System.out.println(result);
//        IPLocateDatxTest2.comp2("F:\\知识\\IP_basic_2018W33_single_WGS84_simple_dat\\test_pro.txt","F:\\知识\\IP_basic_2018W33_single_WGS84_simple_dat\\test_pro_2.txt");
        IPLocateDatxTest2.compare("F:\\知识\\IP_basic_2018W33_single_WGS84_simple_dat\\test_pro_2.txt", "F:\\知识\\IP_basic_2018W33_single_WGS84_simple_dat\\test_owner_2.txt");
    }

}
