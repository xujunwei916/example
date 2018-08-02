package com.example.others.http;

import org.apache.commons.lang.StringUtils;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class TestQPS {

    public static void main(String[] args) throws Exception {

        String json ="{\n" +
                "  \"passid\": \"ssass\",\n" +
                "  \"cookieI\": \"i=76916087&u=77645943&n=%B7%B1%BB%AA%C2%E4%BE%A1wanghq&m=0&t=1531465051.23575600&s=cb6fab0ff122ed6fc596dc9d9ebb5248&v=1.1\",\n" +
                "  \"taskid\": 3,\n" +
                "\"ip\":\"10.0.0.6\",\n" +
                "\"score\":5,\n" +
                "\"appname\":\"2345shouzhu\",\n" +
                "  \"timestamp\": 1531466208,\n" +
                "  \"action\": {\n" +
                "    \"keyword\": \"https://cpu.baidu.com/api/1022/d17b01b7/detail/19863689691699585/news?im=CqqBABJ90g7P52flwZgILbB_D2vWN813xYCL5JzY5-fZyZIEmqnjVhneE4XjAMwpUZgSaw4mg2wJBY1YyXbdqgNKv9RFrwCTPIMM2HhImbB3Xl6zxwRUhVw0A2USQsAH6M3Cd07_jc2ogUuvHFJfGJJdH19YqBEr9Lu7Xc4JnTbRqKSUrSWKXqhwRW_Krzw4W6ngxpKdBqEl3eWnvIt_5XkVCdg0OumnKGvuOpTwn5dvfTSUCe_KadIF_zl0FdHuTrM2lT9s2lpMW0xGQcreLRCidRw70piLbb8pP5kbmNtM6RxrdcQ6uJe0x737Vcf8k5sKRGJg_NkR0yh5E3hTfQ&imMd5=uYcFZtK2oQQgNypDayZmGQFwqVRGcauNJl-gpMS00OIyWVaif2QW3stBOWmCGR6F2t0tGaVthmcpi8-fTxFfcE-ePrkzzRJd1en-PIC4mQHQqN41qM6olKrQHO1DejiCXX2IK6smrja8TWgILvYuBrRKatoeA0rWAeCplGTRNOhTCjCa0R_V7zPuaV3xM84cWHPQur_hejvwRSWBtgouuSur1m0oTEdeLgY8U6NDpuR_mFmDkRUIJlLoW-vNW8dGerJe_utEusptS1RHtfwCswG8xUOZ1DUTv3VD48eU6yJOj0b5RSeF5xrFh7HUmutaSf8-HOr9ZarYykT7q3ML5g&cpid=iQNSbWVNKX-TiaOEK1p0ua0X2oLKhGGKrr5pdCm9SVC60PuLoK1xyNpr8PK9pBUZVyDUpZmBDLL7WxahGmnQ2lEOMjT9Mg1gCn_d6yjO3JNm7xZXcJG8qx3fJhRgM2lgoisauAkpO6lt0hb7Hn6ytj1h4r4EQ3JhAQCFXbk5hBJDamp5Udx1gIJK167-LvEgybDm_jmX_NNILzUyV4VrKCNZqTHyH73iaxdAwQp4gkR5913OjwdtbQNTYsZOWvayoBeL6Atw18WEU8WUVEYysBlVsRhaVOePYan6b_eM59T_krSrMWkH8FupJCPCrKQXJBMG0qHDQGs7LibndTITFg&scene=0&log_id=1531465004674b75bfac6a6c7641ad64&exp_infos=9242_21500_21501_21901_21951_21961_22641_23021_23634_23923_23983_24324_25213_25311_25411_26002_29201_20010&no_list=1&forward=api&api_version=2&rt=8&rts=258\",\n" +
                "    \"remainTime\": 3,\n" +
                "    \"from\": 1,\n" +
                "    \"scrollRange\": 112\n" +
                "  },\n" +
                "  \"client\": {\n" +
                "    \"header\": {\n" +
                "      \"device_model\": \"Letv X501\",\n" +
                "      \"os\": \"Android\",\n" +
                "      \"os_version\": \"6.0\",\n" +
                "      \"resolution\": \"1080*1920\",\n" +
                "      \"access\": \"WiFi\",\n" +
                "      \"package\": \"com.browser2345\",\n" +
                "      \"app_version\": \"9.6beta\",\n" +
                "      \"version_code\": \"127\",\n" +
                "      \"origin_version_name\": \"9.6betadev\",\n" +
                "      \"origin_version_code\": \"127\",\n" +
                "      \"channel\": \"UMENG_CHANNEL_VALUE\",\n" +
                "      \"mainchannel\": \"UMENG_CHANNEL_VALUE\",\n" +
                "      \"local_day\": \"1\",\n" +
                "      \"sdk_version\": \"4.0\",\n" +
                "      \"project_name\": \"browser\",\n" +
                "      \"activate\": 0,\n" +
                "      \"start\": 0,\n" +
                "      \"sys_can_uninstall\": 2,\n" +
                "      \"local_id\": {\n" +
                "        \"imei\": \"868897021504907\",\n" +
                "        \"wmac\": \"847303DCCF8C\",\n" +
                "        \"iccid\": \"\",\n" +
                "        \"imsi\": \"#00686024409934\"\n" +
                "      },\n" +
                "      \"uid\": \"868897021504907\",\n" +
                "      \"uuid\": \"17js9%2Fgwgf4CNfP4smAxTReccY7KogHpi98kDnSxaGmcf%2Fk3PXKqDY%2FeFJEfxBYP\",\n" +
                "      \"android_id\": \"27503caf42fac109\",\n" +
                "      \"brand\": \"Letv\",\n" +
                "      \"build_date\": 1512701187,\n" +
                "      \"battery\": 100,\n" +
                "      \"total_time\": 5115274,\n" +
                "      \"incremental\": \"1512700098\",\n" +
                "      \"manufacturer\": \"Letv\",\n" +
                "      \"serial\": \"4PBULJ7L7POVBAGU\",\n" +
                "      \"blutooth_addr\": \"847303d9c24c\",\n" +
                "      \"screen_brightness\": 120,\n" +
                "      \"volume\": \"0/15\",\n" +
                "      \"ram\": 2662,\n" +
                "      \"rom\": 26147,\n" +
                "      \"ram_remain\": 892,\n" +
                "      \"rom_remain\": 17810,\n" +
                "      \"traffic\": 113667,\n" +
                "      \"hardware\": \"mt6795\",\n" +
                "      \"phone\": \"1871770\",\n" +
                "      \"qq_modify\": 1528679260,\n" +
                "      \"wechat_modify\": -1,\n" +
                "      \"charge_status\": 2,\n" +
                "      \"angle\": -10000,\n" +
                "      \"pass_id\": \"76916087\",\n" +
                "      \"lon\": \"121.601234\",\n" +
                "      \"lat\": \"31.179386\",\n" +
                "      \"send_time\": 1531466208,\n" +
                "      \"rom_os_name\": \"unknow\",\n" +
                "      \"rom_os_version\": \"unknow\",\n" +
                "      \"extend\": \"{\\\"passid\\\":\\\"76916087\\\"}\"\n" +
                "    }\n" +
                "  }\n" +
                "}\n";
        ExecutorService POOL = Executors.newFixedThreadPool(50);
        LinkedBlockingQueue<String> result = new LinkedBlockingQueue<String>();
        long start  = System.currentTimeMillis();
        for (int i = 0; i < 100000 ; i++) {

            POOL.submit(new Runnable() {

                @Override
                public void run() {
                    String response = HttpRequest.sendJSON("http://www.xqprevention.com/cheat_api/realtime_cheat", json);
                    result.add(response);
                }
            });
        }
        POOL.shutdown();
        while (true) {
            if (POOL.isTerminated()) {
                System.out.println("结束了！");
                break;
            }
            Thread.sleep(20);
        }
//        System.out.println(result);
        System.out.println(System.currentTimeMillis()-start);






    }

}
