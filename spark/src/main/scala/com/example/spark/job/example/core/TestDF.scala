package com.example.spark.job.example.core

import com.example.spark.common.SparkJob
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.functions._

object TestDF extends SparkJob {
    override protected def initJob() = {
        sparkConf.setMaster("local[3]")
        sparkConf.setAppName("TestDF")
        init()

    }


    override protected def run(args: Array[String]): Unit = {

        spark.conf
        val ss = spark
        import ss.implicits._
        val df = ss.read.option("header", "true").csv("D://test/test/url.csv")
            .toDF("date", "uid", "url")

        val disDF = df.groupBy("url").agg(countDistinct("date"), countDistinct("uid"), count("uid"))
            .toDF("url", "days", "uv", "pv")
        disDF.withColumn("web",
            when($"url".like("http://%.1666.com/server/%"), "1666").when($"url".like("https://%.1666.com/server/%"), "1666")
                .when($"url".like("http://www.tanwan.com/index.php?act=gamelogin&game_id=%&server_id=%"), "贪玩").when($"url".like("https://www.tanwan.com/index.php?act=gamelogin&game_id=%&server_id=%"), "贪玩")
                .when($"url".like("http://game.37.com/play.php?game_id=%&sid=%"), "37游戏").when($"url".like("https://game.37.com/play.php?game_id=%&sid=%"), "37游戏")
                .when($"url".like("http://web.4399.com/stat/togame.php?target=%&server_id=%"), "4399").when($"url".like("https://web.4399.com/stat/togame.php?target=%&server_id=%"), "4399")
                .when($"url".like("http://niu.xunlei.com/entergame/%/"), "迅雷牛X").when($"url".like("https://niu.xunlei.com/entergame/%/"), "迅雷牛X")
                .when($"url".like("http://udblogin.duowan.com/login.do?game=%&server=%"), "多玩").when($"url".like("https://udblogin.duowan.com/login.do?game=%&server=%"), "多玩")
                .when($"url".like("http://%.yy.%.chuangquannet.cn/game.php"), "YY游戏").when($"url".like("https://%.yy.%.chuangquannet.cn/game.php"), "YY游戏")
                .when($"url".like("http://wan.sogou.com/play.do?gid=%&sid=%"), "搜狗游戏").when($"url".like("https://wan.sogou.com/play.do?gid=%&sid=%"), "搜狗游戏")
                .when($"url".like("http://www.xy.com/game/play/%/%"), "XY游戏").when($"url".like("https://www.xy.com/game/play/%/%"), "XY游戏")
                .when($"url".like("http://game.youzu.com/play?gameId=%&serverId=%"), "游族游戏").when($"url".like("https://game.youzu.com/play?gameId=%&serverId=%"), "游族游戏")
                .when($"url".like("http://www.game2.cn/joinGame/code/%/"), "哥们网").when($"url".like("https://www.game2.cn/joinGame/code/%/"), "哥们网")
                .when($"url".like("http://www.91wan.com/user/game_login.php?game_id=%&server_id=%"), "91玩").when($"url".like("https://www.91wan.com/user/game_login.php?game_id=%&server_id=%"), "91玩")
                .when($"url".like("http://1.qq.com/help/startgameV2.htm?iAppid=%&iServerid=%"), "腾讯页游").when($"url".like("https://1.qq.com/help/startgameV2.htm?iAppid=%&iServerid=%"), "腾讯页游")
                .when($"url".like("http://www.53wan.com/Game-%-%.html"), "53玩").when($"url".like("https://www.53wan.com/Game-%-%.html"), "53玩")
                .when($"url".like("http://www.1k2k.com/game_play/%.html"), "1k2k").when($"url".like("https://www.1k2k.com/game_play/%.html"), "1k2k")
                .when($"url".like("http://www.160yx.com/ddt/play.html?gid=%&sid=%"), "160YX").when($"url".like("https://www.160yx.com/ddt/play.html?gid=%&sid=%"), "160YX")
                .when($"url".like("http://wan.douyu.com/game_frame/play_%.php?sid=%"), "斗鱼页游").when($"url".like("https://wan.douyu.com/game_frame/play_%.php?sid=%"), "斗鱼页游")
                .when($"url".like("http://www.29yx.com/Playgame/%/%"), "29YX").when($"url".like("https://www.29yx.com/Playgame/%/%"), "29YX")
                .when($"url".like("http://wvw.9377.com/game_login.php?game=%&server=%"), "9377").when($"url".like("https://wvw.9377.com/game_login.php?game=%&server=%"), "9377")
                .when($"url".like("http://web.7k7k.com/stat/togame.php?target=%"), "7K7K").when($"url".like("https://web.7k7k.com/stat/togame.php?target=%"), "7K7K")
                .when($"url".like("http://play.9211.com/p/%/%?platformSource=%"), "9191").when($"url".like("https://play.9211.com/p/%/%?platformSource=%"), "9191")
                .when($"url".like("http://my.51wan.com/game_toolbar%.html"), "51玩").when($"url".like("https://my.51wan.com/game_toolbar%.html"), "51玩")
                .when($"url".like("http://www.17iy.com/gamelogin.html?gid=%&sid=%"), "17iy").when($"url".like("https://www.17iy.com/gamelogin.html?gid=%&sid=%"), "17iy")
                .when($"url".like("http://wan.265g.com/ftj//play/%"), "265g").when($"url".like("https://wan.265g.com/ftj//play/%"), "265g")
                .when($"url".like("http://www.wan8.com/game/login?game_id=%&server_id=%"), "玩吧").when($"url".like("https://www.wan8.com/game/login?game_id=%&server_id=%"), "玩吧")
                .when($"url".like("http://bbs.3dmgame.com/plugin.php?id=%"), "3DM").when($"url".like("https://bbs.3dmgame.com/plugin.php?id=%"), "3DM")
                .when($"url".like("http://www.feihuo.com/game/play/serverid/%"), "飞火游戏").when($"url".like("https://www.feihuo.com/game/play/serverid/%"), "飞火游戏")
                .when($"url".like("http://www.qweol.com/%/%"), "51游戏").when($"url".like("https://www.qweol.com/%/%"), "51游戏")
                .when($"url".like("http://cpgame.qd.game.qidian.com/Home/Index/%/gameId/%/serverId/%"), "起点游戏").when($"url".like("https://cpgame.qd.game.qidian.com/Home/Index/%/gameId/%/serverId/%"), "起点游戏")
                .when($"url".like("http://www.qq8.com.cn/%/%"), "QQ8").when($"url".like("https://www.qq8.com.cn/%/%"), "QQ8")
                .when($"url".like("http://api.shunwangdts.6ght.com/client/?user_name=%&server_id=%"), "顺网").when($"url".like("https://api.shunwangdts.6ght.com/client/?user_name=%&server_id=%"), "顺网")
                .when($"url".like("http://gamecenter.kuwo.cn/jumpgame/index?gameid=%"), "酷我").when($"url".like("https://gamecenter.kuwo.cn/jumpgame/index?gameid=%"), "酷我")
                .when($"url".like("http://gamecenter.kugou.com/jumpgame/index?gameid=%"), "酷狗").when($"url".like("https://gamecenter.kugou.com/jumpgame/index?gameid=%"), "酷狗")
                .when($"url".like("http://playgame.iqiyi.com/login/iframe_page_web/top?game_id=%&server_order=%"), "爱奇艺").when($"url".like("https://playgame.iqiyi.com/login/iframe_page_web/top?game_id=%&server_order=%"), "爱奇艺")
                .when($"url".like("http://yyj.yilewan.com/gamePlay/play?gameId=%&serverId=%"), "易乐玩").when($"url".like("https://yyj.yilewan.com/gamePlay/play?gameId=%&serverId=%"), "易乐玩")
                .when($"url".like("http://cqsd.wan.360.cn/game_login.php?server_id=%"), "360").when($"url".like("https://cqsd.wan.360.cn/game_login.php?server_id=%"), "360")
                .when($"url".like("http://www.5399.com/login_game/%"), "5399").when($"url".like("https://www.5399.com/login_game/%"), "5399")
                .when($"url".like("http://cqss.yaodou.com/%"), "妖豆").when($"url".like("https://cqss.yaodou.com/%"), "妖豆")
                .when($"url".like("http://www.52xiyou.com/playgame_%html"), "西游网").when($"url".like("https://www.52xiyou.com/playgame_%html"), "西游网")
                .otherwise("错误")
        ).select("web", "days", "uv", "pv", "url")
            .orderBy("web", "url")
            .repartition(1).write
            .mode(SaveMode.Overwrite).csv("D://test//test//url")


        spark.sparkContext.addJar("file:///opt/case/guess.union2.50bang.org/spark/target/com.guess2345-1.0.jar")


        spark.stop()
    }
}
