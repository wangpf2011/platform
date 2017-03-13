package com.wf.xutils3.bean;

import org.xutils.db.annotation.Column;

/**
 * 实体BaseEntity
 * Created by wangpf on 2016/11/8.
 */

public class BaseEntity {
    //id
    @Column(name = "id", isId = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
