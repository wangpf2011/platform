package com.lnint.pattern.mvp.model;

/**
 * 网络请求返回bean
 * Created by wangpf on 2016/11/17.
 */

public class RespInfo {
    private String data;
    private String success = "false";
    private String message;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
