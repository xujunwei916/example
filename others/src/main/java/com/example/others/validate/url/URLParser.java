package com.example.others.validate.url;

import com.google.common.net.InternetDomainName;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析URL的域名个一级路径
 */
public class URLParser {

    private static final String HTTP = "^(?:https?://)?";

    private static final String DOMAIN = "((?:[a-z0-9-_]{1,63}\\.){1,}[a-z]{2,})";

    private static final String PATH_MULTI = "(?:/([^\\?]+))?.*$";

    private Pattern domainPattern;

    private String left;

    private String right;

    private URLParser() {
        domainPattern = Pattern.compile(HTTP + DOMAIN + PATH_MULTI, Pattern.CASE_INSENSITIVE);
    }


    /**
     * @param left  未知域名 UNKNOWN
     * @param right 未知路径  空串
     * @return
     */
    private static URLParser create(String left, String right) {
        URLParser instance = new URLParser();
        instance.left = left;
        instance.right = right;
        return instance;
    }

    /**
     * 转换域名生成域名和路径
     *
     * @param url url
     * @return key为域名, value为路径 路径不带/
     */
    public Pair<String, String> parseDomain(String url) {
        MutablePair<String, String> pair = new MutablePair<String, String>();
        pair.setLeft(left);
        pair.setRight(right);

        if (StringUtils.isNotBlank(url)) {
            Matcher matcher = domainPattern.matcher(url);

            if (matcher.find()) {
                String domain = matcher.group(1).toLowerCase();
                pair.setLeft(domain);

                String path = matcher.group(2);
                if (path != null) {
                    pair.setRight(path);
                }
            }
        }
        return pair;
    }

    /**
     * 获取域名的父域名列表
     *
     * @param domain 域名
     * @return
     */
    public List<String> parentDomain(String domain) {
        List<String> domains = new ArrayList<String>();
        if ("UNKNOWN".equals(domain) || StringUtils.isEmpty(domain)) {
            return domains;
        }
        if (!InternetDomainName.isValid(domain)) {
            return domains;
        }
        InternetDomainName domainName = InternetDomainName.from(domain);
        domains.add(domainName.toString());
        while (!domainName.isTopPrivateDomain() && domainName.hasParent()) {
            domainName = domainName.parent();
            domains.add(domainName.toString());
        }
        return domains;
    }


    public List<String> parentPath(Pair<String, String> pair) {
        List<String> result = new ArrayList<>();
        if (StringUtils.isEmpty(pair.getRight())||"UNKNOWN".equals(pair.getLeft()) || StringUtils.isEmpty(pair.getLeft())) {
            return result;
        }
        String paths[] = pair.getRight().split("/");

        StringBuffer sb = new StringBuffer(100);
        for (String path : paths) {
            sb.append("/");
            sb.append(path);
            result.add(pair.getLeft() + sb.toString());
        }

        return result;

    }

    public static class Holder {
        static URLParser parser = URLParser.create("UNKNOWN", "");
        ;

        public static URLParser getParser() {
            return parser;
        }

    }


    public static void main(String[] args) throws Exception {


        Pair<String, String> pair = URLParser.Holder.getParser().parseDomain("abc.annn.com.cn/abbb/asdkkfkd/djdj?anc=1");
        List<String> paths = URLParser.Holder.getParser().parentPath(pair);
        List<String> domains = URLParser.Holder.getParser().parentDomain(pair.getKey());
        System.out.println(pair);
        System.out.println(paths);
        System.out.println(domains);


    }
}