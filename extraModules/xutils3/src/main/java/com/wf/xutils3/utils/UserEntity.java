package com.wf.xutils3.utils;

/**
 * xUtilsDemo所属实体
 * Created by wangpf on 2017/4/19.
 */

public class UserEntity {
    private String name;

    private String tel;

    public UserEntity(String name, String tel) {
        this.name = name;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
