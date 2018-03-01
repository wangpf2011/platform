package com.wf.xutils3.demo;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * xUtils3.x数据库操作实体
 *
 * onCreated = "sql"：当第一次创建表需要插入数据时候在此写sql语句
 *
 * Created by wangpf on 2017/5/5.
 */
@Table(name = "child_info",onCreated = "")
public class ChildInfo {
    /**
     * name = "id"：数据库表中的一个字段
     * isId = true：是否是主键
     * autoGen = true：是否自动增长
     * property = "NOT NULL"：添加约束
     */
    @Column(name = "id",isId = true,autoGen = true,property = "NOT NULL")
    private int id;
    @Column(name = "c_name")
    private String cName;

    public ChildInfo(String cName) {
        this.cName = cName;
    }
    //默认的构造方法必须写出，如果没有，这张表是创建不成功的
    public ChildInfo() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getcName() {
        return cName;
    }
    public void setcName(String cName) {
        this.cName = cName;
    }
    @Override
    public String toString() {
        return "ChildInfo{"+"id="+id+",cName='"+cName+'\''+'}';
    }
}
