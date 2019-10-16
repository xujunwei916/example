package com.example.others.text;

import com.google.common.collect.Lists;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws Exception {

//        List<String> line1 =IOUtils.readLines(new FileReader("C:\\Users\\xujw\\Desktop\\tmp\\imei_t6.txt"));
//        List<String> line2 =IOUtils.readLines(new FileReader("C:\\Users\\xujw\\Desktop\\tmp\\imei_50bang.txt"));
//
//        List<String> result =ListUtils.removeAll(line2,line1);
//
//        FileWriter writer = new FileWriter("C:\\Users\\xujw\\Desktop\\tmp\\other.txt");
//        IOUtils.writeLines(result,null,writer);
//        writer.close();
//        Object s = 1;
//        System.out.println(s instanceof Long);
//        DateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        List<String> lines = IOUtils.readLines(new FileReader("D://tmp/timestamp.txt"));
//        lines.sort(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });
//        for (String line: lines
//             ) {
//            System.out.println(SDF.format(new Date(Long.valueOf(line))) );
//
//        }

//        System.out.println("[\"10004501\",\"10004502\",\"10004503\"]".replaceAll("\\[|\\]|\"|'", ""));
//
//        Integer a =-1;
//        int b=-1;
//        System.out.println(a==b);

//        Set<String > list = new HashSet();
//        Set<String > list2 = new HashSet();
//
//        String []aaa = new String[]{
//                "com.airbnb.android","com.shuqi.controller","com.popnews2345","com.baidu.minivideo","com.skyplatanus.crucio","com.xueersi.parentsmeeting","com.kmxs.reader","com.qihoo.cleandroid_cn","com.sup.android.superb","com.eg.android.AlipayGphone","com.xunmeng.pinduoduo","com.pumpkinteam.pumpkinplayer","com.yoho","cn.weli.story","com.kidplay.bbtgs","com.qq.reader","com.xiaomi.youpin","com.wuba.bangjob","cn.caocaokeji.user","com.ganji.android.haoche_c","com.yiche.price","com.duxiaoman.umoney","com.qiyi.video","com.xiaozhu.xzdz","com.jzyd.coupon","com.hunting.matrix_callershow","com.shizhuang.duapp","com.pingan.papd","com.gotokeep.keep","com.lianjia.beike","com.ximalaya.ting.android","cn.missfresh.application","com.ldzs.zhangxin","com.dianping.v1","com.jingdong.app.mall","com.netease.newsreader.activity","com.arvato.sephora.app","com.youku.phone","com.hpbr.bosszhipin","com.sohu.sohuvideo","com.UCMobile","com.tencent.qqmusic","com.baidu.searchbox","com.jd.jrapp","com.komoxo.octopusime","com.guazi.newcar","com.anjuke.android.app","com.tencent.karaoke","com.ichinait.gbpassenger","com.dangdang.buy2","com.tencent.weishi","com.ushaqi.zhuishushenqi.adfree","com.job.android","com.smzdm.client.android","com.qiyi.video.child","cn.youth.news","com.booking","com.duowan.mobile","com.mengtuiapp.mall","com.tianyancha.skyeye","com.taobao.etao","com.shuhekeji","com.songheng.eastnews","com.huanshou.taojj","tv.yixia.bobo","com.leshuwu.qiyou","com.ifeng.news2","com.taobao.idlefish","com.Qunar","com.sina.weibo","com.kugou.fanxing","com.p1.mobile.putong","com.tujia.hotel","com.tencent.news","com.achievo.vipshop","com.tuniu.app.ui","com.jfbank.wanka","com.taobao.taobao","air.tv.douyu.android","com.ziroom.ziroomcustomer","com.qukandian.video","com.tencent.firevideo","com.taptap","com.smile.gifmaker","com.wuba","com.zhongan.insurance","com.bokecc.dance","com.baidu.searchbox.lite","com.baidu.haokan","com.pplive.androidphone.sport","com.yuewen.cooperate.reader.free","com.gh.gamecenter"
//        };
//        String []bbb = new String []{
//                "com.meitu.meiyancamera","com.sinyee.babybus.adventure","com.baidu.minivideo","com.ubisoft.rabbidsrunner.vivo","com.sinyee.babybus.zoo","com.sinyee.babybus.wonderland","com.supercell.clashroyale.vivo","com.sinyee.babybus.findCha","com.yiru.ggtkal.vivo","com.eg.android.AlipayGphone","com.kiloo.subwaysurf","com.xunmeng.pinduoduo","com.mars.bx.vivo","com.dilong.mqfqt.vivo","com.melestudio.jigsaw.vivo","com.tencent.mobileqq","com.sinyee.babybus.organized","com.tongzhuo.tongzhuogame","com.ManackerGame.Aquapark.gtx","com.ultralisk.game24.vivo","com.sinyee.babybus.numberwriter","com.sinyee.babybus.coffee","com.sinyee.babybus.foodstory","com.popcap.pvz2cthdbbg","com.happyelements.AndroidAnimal","com.sinyee.babybus.hero","com.ubestkid.beilehu.android","com.ckhd.jpzmg.vivo","com.fgol.hsw.vivo","com.le123.ysdq","com.mampod.ergedd","com.sinyee.babybus.colour","com.baidu.appsearch","com.sinyee.babybus.food","com.changle.fistfight.vivo","com.xingyugame.gt.vivo","com.geniustime.bbxdj.vivo","com.shusheng.JoJoSherlock","com.libii.lovediary.vivo","com.JindoBlu.Antistress","cn.ultralisk.gameapp.game23.vivo","com.sinyee.babybus.toilet","com.qiyi.video","com.sinyee.babybus.restaurant","com.sinyee.babybus.hospital","com.sinyee.babybus.homeland","com.tencent.mtt","com.tencent.nntg.linekong","com.cleanmaster.mguard_cn","com.sinyee.babybus.photostudio","com.ss.android.ugc.live","com.melon.lazymelon","com.cleanmaster.security_cn","com.kuaiduizuoye.scan","com.jingdong.app.mall","com.mfp.jelly.vivo","com.tianqi2345","com.changba","com.youku.phone","com.wepie.snake.vivo","com.UCMobile","com.xuanliang.dwsd.vivo","com.martian.gddtq.vivo","com.sinyee.babybus.automobilecity","com.tencent.qqmusic","com.baidu.searchbox","tv.pps.mobile","com.tgelec.aqsh","com.sinyee.babybus.expedition","com.ctvit.cctvchildren","com.sinyee.babybus.shopping","com.sinyee.babybus.findnumber","com.sinyee.babybus.bird","zz.dela.cmcc.traffic","com.martian.gqdzds.vivo","com.sinyee.babybus.earthquakeIII","dopool.player","com.sinyee.babybus.habits","com.sinyee.babybus.nurseryrhyme","com.wifi.reader.free","com.netease.cloudmusic","com.huizheng.kya","com.sinyee.babybus.princess","com.tencent.android.qqdownloader","com.joym.combatman.vivo","com.kid58.lianyong.salon","com.baidu.homework","com.snda.wifilocating","com.sinyee.babybus.natural","com.pape.nuannew.self.vivo","cn.kuwo.player","com.kugou.android","com.sinyee.babybus.assistant","com.videogo","com.hht.zjw","com.sinyee.babybus.karting","com.cmplay.tiles2_cn.vivo","cn.cj.pe","com.memezhibo.android","com.taobao.idlefish","cn.opda.a.phonoalbumshoushou","com.sinyee.babybus.talk2kiki","com.sinyee.babybus.kitchens","com.oneapp.max.cn","com.tencent.mm","com.guagualongkids.android","com.tencent.qqpimsecure","com.ss.android.article.video","com.tencent.news","com.achievo.vipshop","com.sinyee.babybus.recommendapp","com.sinyee.babybus.picnic","com.wb.gddsj.vivo","com.cameras.prettygirls","com.sinyee.babybus.culture","com.sinyee.babybus.puzzle","com.ss.android.article.lite","com.sinyee.babybus.seaworld","com.weibo.comic","com.tencent.qqlivekid","com.sinyee.babybus.snacks","com.doding.puzzleking","com.ss.android.ugc.aweme","com.sinyee.babybus.greenhouse","com.tencent.qqlive","com.smile.gifmaker","com.cmcc.cmvideo","com.youloft.calendar","com.sinyee.babybus.repair","com.sinyee.babybus.ant","com.zhongan.insurance","com.sinyee.babybus.clean","com.minitech.miniworld.vivo","com.sinyee.babybus.chants","com.asiainno.uplive","com.fingertip.tyt.vivo","com.sinyee.babybus.earthquakeII","com.baidu.haokan","com.nxy.mobilebank.hb","com.sinyee.babybus.manor","com.sinyee.babybus.taxi"
//        };
//
//        list.addAll(Arrays.asList(aaa));
//        list2.addAll(Arrays.asList(bbb));
//
//
//        list.retainAll(list2);
//        System.out.println(list);
        String ss="WARN  2019-09-26 13:18:54,818 [pool-5-thread-2] JobSubmitter - <--- excute_id \"1117710,\"task_id:1\", signal Files: dw_product_safe_realtime_click_log_opt,等待信>    号文件 --->>>";

        Pattern pattern = Pattern.compile("[A-Za-z]*\\s+(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{1,3})\\s+\\[.+\\].*$");
        Matcher matcher = pattern.matcher(ss);
        if (matcher.find()) {

            String time = matcher.group(1);
            System.out.println(time);
        }





    }
}
