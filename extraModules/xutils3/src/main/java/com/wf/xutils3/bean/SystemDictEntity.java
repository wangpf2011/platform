package com.wf.xutils3.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 保存系统字典信息
 * Created by wangpf on 2016/7/18.
 */
@Table(name = "ev_system_dict")
public class SystemDictEntity extends BaseEntity {
    //字典名称
    @Column(name = "name")
    private String name;
    //字典值
    @Column(name = "value")
    private String value;
    //字典类型
    @Column(name = "type")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
