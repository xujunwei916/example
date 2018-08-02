package com.example.others.gson;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GsonMap {
    public static void main(String[] args) {
        Gson GSON = new Gson();
        Type listType = new TypeToken<Map<String, Object>>() {
        }.getType();
        String s = "{\"packageName\":\"ads.mobile2345.com.mobileadsplatform3\",\"versionCode\":\"1\",\"channel\":\"cu1000490\",\"versionName\":\"1.0\",\"sdkVersion\":\"1.1.0\",\"data\":{\"taskId\":100036,\"type\":6,\"action\":\"pull\",\"triggerType\":\"1\"},\"device\":{\"imei\":\"869580026876228\",\"deviceType\":1,\"brand\":\"OPPO\",\"model\":\"OPPO A30\",\"oem\":\"OPPO\",\"os\":\"Android\",\"osv\":\"5.1.1\",\"osid\":\"2d0bf8262deb708a\",\"network\":1,\"operator\":0,\"imsi\":\"#89890801282068\",\"ip\":\"172.17.96.199\",\"lon\":\"0.0\",\"lat\":\"0.0\",\"width\":\"1080\",\"height\":\"1920\",\"orientation\":\"1\",\"romOsName\":\"Color OS\",\"romOsVersion\":\"2.1\"},\"user\":{\"uid\":\"14073C0E4639256A7D9C1332FB08\"},\"test001\":\"test001\",\"test002\":20000,\"test003\":{\"aaaa\":\"aaaaa\",\"bbbbbb\":123}}";

        Context context = GSON.fromJson(s, Context.class);
        Map<String, Object> map = GSON.fromJson(s, listType);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (Context.defaultName.contains(entry.getKey())) {

            } else {
                Object value = entry.getValue();

                if (value instanceof List || value instanceof Map) {
                    context.getOthers().put(entry.getKey(), GSON.toJson(value));
                } else {
                    context.getOthers().put(entry.getKey(), value.toString());
                }

            }
        }
        System.out.println(context);
        System.out.println(map);
    }

}

class Data {
    private Long taskId;
    private Integer type;
    private String action;
    private String triggerType;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    @Override
    public String toString() {
        return "Data{" +
                "taskId=" + taskId +
                ", type=" + type +
                ", action='" + action + '\'' +
                ", triggerType='" + triggerType + '\'' +
                '}';
    }
}

class User {
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                '}';
    }
}