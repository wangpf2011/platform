package com.lnint.example.sqlite;

import com.lidroid.xutils.db.annotation.Column;

/**
 * 实体BaseEntity
 * Created by wangpf on 2016/11/8.
 */

public class BaseEntity {
    //id
    @Column(column = "id")
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
