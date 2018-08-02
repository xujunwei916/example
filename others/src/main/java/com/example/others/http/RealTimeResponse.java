package com.example.others.http;

import com.google.gson.annotations.SerializedName;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 实时反作弊的响应数据
 */
public class RealTimeResponse implements Serializable {


    private Integer grant = 0;
    private Integer cheat=0;
    private Integer freeze=0;
    @SerializedName("cheat_type")
    private List<Integer> cheatType = new ArrayList<>();
    private String message;
    public Integer getGrant() {
        return grant;
    }

    public void setGrant(Integer grant) {
        this.grant = grant;
    }
    public Integer getCheat() {
        return cheat;
    }

    public void setCheat(Integer cheat) {
        this.cheat = cheat;
    }

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }

    public List<Integer> getCheatType() {
        return cheatType;
    }

    public void setCheatType(List<Integer> cheatType) {
        this.cheatType = cheatType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "RealTimeResponse{" +
                "grant=" + grant +
                ", cheat=" + cheat +
                ", freeze=" + freeze +
                ", cheatType=" + cheatType +
                ", message='" + message + '\'' +
                '}';
    }
}
