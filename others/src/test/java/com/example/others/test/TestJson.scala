package com.example.others.test

import com.fasterxml.jackson.databind.ObjectMapper
object TestJson {

    def main(args: Array[String]): Unit = {

        val s= "{\"data\":{\"date\":\"2018-04-26\",\"installedAppList\":\"[{\\\"appName\\\":\\\"5.4.0.66_r807534\\\",\\\"package\\\":\\\"com.tencent.mm\\\",\\\"version\\\":\\\"480\\\"},{\\\"appName\\\":\\\"5.3.3\\\",\\\"package\\\":\\\"com.kingroot.kinguser\\\",\\\"version\\\":\\\"193\\\"},{\\\"appName\\\":\\\"1.0\\\",\\\"package\\\":\\\"ads.mobile2345.com.mobileadsplatform2\\\",\\\"version\\\":\\\"1\\\"},{\\\"appName\\\":\\\"1.0\\\",\\\"package\\\":\\\"ads.mobile2345.com.mobileadsplatform1\\\",\\\"version\\\":\\\"1\\\"},{\\\"appName\\\":\\\"3.1\\\",\\\"package\\\":\\\"com.jumobile.apk.mgr\\\",\\\"version\\\":\\\"301\\\"},{\\\"appName\\\":\\\"3.18.1100(SL)_VER_32526089295372\\\",\\\"package\\\":\\\"com.qiku.powermaster\\\",\\\"version\\\":\\\"318\\\"},{\\\"appName\\\":\\\"4.7.7\\\",\\\"package\\\":\\\"com.speedsoftware.rootexplorer\\\",\\\"version\\\":\\\"999477\\\"}]\",\"runningAppList\":\"[{\\\"appName\\\":\\\"4.7.7\\\",\\\"package\\\":\\\"com.speedsoftware.rootexplorer\\\",\\\"version\\\":\\\"999477\\\"},{\\\"appName\\\":\\\"5.3.3\\\",\\\"package\\\":\\\"com.kingroot.kinguser\\\",\\\"version\\\":\\\"193\\\"},{\\\"appName\\\":\\\"1.0\\\",\\\"package\\\":\\\"ads.mobile2345.com.mobileadsplatform1\\\",\\\"version\\\":\\\"1\\\"}]\"},\"device\":{\"orientation\":1,\"os\":\"Android\",\"model\":\"Coolpad 8675-FHD\",\"height\":1920,\"osv\":\"4.4.4\",\"deviceType\":1,\"width\":1080,\"imei\":\"866394021855143\",\"brand\":\"Coolpad\",\"operator\":0,\"network\":1},\"app\":{\"appid\":0,\"versionName\":\"1.0\",\"packageName\":\"ads.mobile2345.com.mobileadsplatform1\",\"channel\":\"cu1000490\",\"version\":\"1\"},\"adsenseid\":-1,\"user\":{\"uid\":\"96d99a5f1c6eee339414c74560f03cac\"}}";
        val objectMapper =new ObjectMapper()
        val result = objectMapper.readValue(s,classOf[JsonBean])
        println(result.data.date)




    }



}
