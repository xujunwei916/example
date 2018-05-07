package com.example.others.webmagic.vo;

import java.util.List;

/**
 * 使用scripts 加载信息的json对象
 *<script>FM.view({"ns":"","domid":"Pl_Third_Inline__5","css":[],"html":"    "})</script>
 *
 *
 * @author xujw
 *
 */
public class FMView {

    public final static String REGEX_FM_VIEW_JSON="FM.view\\((.*)\\)";

    private String ns;
    private String domid;
    private List<String> css;
    private String html;

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    public String getDomid() {
        return domid;
    }

    public void setDomid(String domid) {
        this.domid = domid;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }


    @Override
    public String toString() {
        return "FMView{" +
                "ns='" + ns + '\'' +
                ", domid='" + domid + '\'' +
                ", css=" + css +
                ", html='" + html + '\'' +
                '}';
    }
}
