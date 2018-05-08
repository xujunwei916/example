package com.example.others.webmagic;

import com.google.gson.Gson;

import com.example.others.webmagic.vo.FMView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

public class SinaWeiboProcesser implements PageProcessor {

    private String tagPattern = "item_ico W_fl(.*?)<\\\\/span";

    private Gson GSON = new Gson();

    @Override
    public void process(Page page) {

        Html html = page.getHtml();
        Selectable selectable = html.$("script").regex(FMView.REGEX_FM_VIEW_JSON ,1);
        Map<String,FMView>  fmViewMap=  new HashMap<>();
        for (String fmViewJson:selectable.all() ) {
            FMView fmView= GSON.fromJson(fmViewJson,FMView.class);
            fmViewMap.put(fmView.getDomid(),fmView);
        }

        FMView userInfoFMView = fmViewMap.get("Pl_Core_UserInfo__6");
        if(userInfoFMView!=null){
            processUserInfo(userInfoFMView);
        }



    }

    @Override
    public Site getSite() {

        return Site.me()
                .setCycleRetryTimes(3)
                .setTimeOut(1000 * 10)
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.108 Safari/537.36 2345Explorer/8.6.1.15524")
                .addHeader("Cookie", "SINAGLOBAL=59732223163.12254.1525428304433; YF-Ugrow-G0=57484c7c1ded49566c905773d5d00f82; login_sid_t=765938cbb4a674b643a2477ef53d0aed; cross_origin_proto=SSL; YF-V5-G0=b59b0905807453afddda0b34765f9151; WBStorage=96e2695964e412de|undefined; SUB=_2AkMtsyT7f8NxqwJRmP0WyGPkbYp2zwnEieKb79UgJRMxHRl-yT83qkxStRB6BjMKFEjPeyRrdF8HufEjffEJGIFlH1zR; SUBP=0033WrSXqPxfM72-Ws9jqgMF55529P9D9W5RVoIenAiNPyOm5--wqfH6; _s_tentry=passport.weibo.com; Apache=7296326477365.207.1525656526212; wb_view_log=1366*7681; ULV=1525656526265:2:2:1:7296326477365.207.1525656526212:1525428304840; YF-Page-G0=b98b45d9bba85e843a07e69c0880151a");
    }


    public static void processUserInfo(FMView fmView){

        String htmlStr = fmView.getHtml();
        Html html = new Html(htmlStr);
        Selectable selectable=  html.$("span").$("[class='item_ico W_fl']")
                .$("em");
        for (Selectable line :selectable.nodes()){
            System.out.println(line.$("em","class").get()+" = "+line.$("em","text").get());
        }
//        Document doc =Jsoup.parse(htmlStr);
//        Elements elements =doc.select("span").select("[class='item_ico W_fl']").select("em");
//        for (Element el:elements){
//            System.out.println(el.toString());
//        }
//        for ()
//       for (Element element:doc.getAllElements()){
//           System.out.println(element.t);
//       }
//        System.out.println(htmlStr);


    }

    /**
     * 根据传入微博Uid构建待爬取的微博页面
     */
    private static String getWeiboUrl(String uid) {
        String targetRequestUrl = "http://weibo.com";
        if (uid.startsWith("100")) {
            targetRequestUrl += "/p/" + uid + "?is_all=1";
        } else if (uid.matches("[0-9]+")) {
            targetRequestUrl += "/u/" + uid + "?is_all=1";
        } else {
            targetRequestUrl += "/" + uid + "?is_all=1";
        }
        return targetRequestUrl;
    }

    public static void main(String[] args) {
        String uid = "1291477752";
        System.out.println(getWeiboUrl(uid));
        Spider weiboSpider = Spider.create(new SinaWeiboProcesser());


        weiboSpider.addUrl(getWeiboUrl(uid));
        weiboSpider.thread(5).run();
        System.out.println(weiboSpider.getStatus());
    }
}
