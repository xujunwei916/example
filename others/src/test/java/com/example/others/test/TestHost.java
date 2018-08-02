package com.example.others.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestHost {
    public static void main(String[] args) throws UnknownHostException {
//       String aaa= InetAddress.getLocalHost().getCanonicalHostName();
//        System.out.println(aaa);


        String ss[] = new String[]{"local_day", "incremental", "serial", "traffic", "volume"
        };
        String s = " case \"uid\":{\n" +
                "                            if (value == null){\n" +
                "                                this.uid=\"\";\n" +
                "                            } else if(value instanceof String) {\n" +
                "                                this.uid = (String) value;\n" +
                "                            }else if(value instanceof List || value instanceof Map){\n" +
                "                                this.headers.put(\"uid\",GSON.toJson(value));\n" +
                "                            }else {\n" +
                "                                this.uid = value.toString();\n" +
                "                            }\n" +
                "                            break;\n" +
                "                        }";
        for (String t : ss) {
            System.out.println(s.replaceAll("uid", t));

        }


        System.out.println("");

    }
}
