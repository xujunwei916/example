package com.example.others.http;

import java.util.HashMap;
import java.util.Map;


/**
 * 实时反作弊实际接收的请求
 */
public class RequestJSON {


    private Map<String, Object> client = new HashMap<>();
    private String ip;
    private String passid;
    private String taskid;
    private Object data;
    private Integer score;
    private String appname;
    private Object action;


    public Map<String, Object> getClient() {
        return client;
    }

    public void setClient(Map<String, Object> client) {
        this.client = client;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPassid() {
        return passid;
    }

    public void setPassid(String passid) {
        this.passid = passid;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getAction() {
        return action;
    }

    public void setAction(Object action) {
        this.action = action;
    }

}
