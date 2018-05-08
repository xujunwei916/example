package com.example.others.hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

public class HanLpLanguage {
    public static void main(String[] args) {
       List<Term> list= HanLP.segment("其实很简单，举个例子就明白了。 Nike 公司想在网上打广告推出自己的一款新鞋子，目标客户是20岁左右的男性大学生。推广过程包含4个方面：");
        for (Term term:list             ) {
            System.out.println(term);
        }
    }
}
