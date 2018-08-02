package com.example.others.gson;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Context {

    public static Set<String> defaultName = new HashSet<String>();

    static {
        defaultName.add("packageName");
        defaultName.add("versionCode");
        defaultName.add("channel");
        defaultName.add("versionName");
        defaultName.add("sdkVersion");
        defaultName.add("data");
        defaultName.add("device");
        defaultName.add("user");
    }

    private String packageName;
    private String versionCode;
    private String channel;
    private String versionName;
    private String sdkVersion;
    private Data data;
    private Map<String, String> device = new HashMap<>();
    private User user;
    private Map<String, String> others = new HashMap<>();

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Map<String, String> getDevice() {
        return device;
    }

    public void setDevice(Map<String, String> device) {
        this.device = device;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, String> getOthers() {
        return others;
    }

    public void setOthers(Map<String, String> others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return "Context{" +
                "packageName='" + packageName + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", channel='" + channel + '\'' +
                ", versionName='" + versionName + '\'' +
                ", sdkVersion='" + sdkVersion + '\'' +
                ", data=" + data +
                ", device=" + device +
                ", user=" + user +
                ", others=" + others +
                '}';
    }
}
