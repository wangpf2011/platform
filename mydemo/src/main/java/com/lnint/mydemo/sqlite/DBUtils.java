package com.lnint.mydemo.sqlite;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作工具类
 * Created by wangpf on 2016/11/8.
 */

public class DBUtils<T> {
    // 查询所有对象
    public List<T> findAllData(T t, Context context) {
        List<T> dataList = new ArrayList<T>();
        try {
            DbUtils db = DbUtils.create(context, "myframe.db");
            //db.configAllowTransaction(true);
            //db.configDebug(true);
            //db.deleteAll(TradeRecEntity.class);
            //数据结果集
            dataList = db.findAll(Selector.from(t.getClass()));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    // 根据条件查询对象集合
    public List<T> findDataList(T t, Context context) {
        int pageSize = 10;
        int pageIndex = 0;
        List<T> dataList = new ArrayList<T>();
        try {
            DbUtils db = DbUtils.create(context, "myframe.db");
            // WHERE id<54 AND (age>20 OR age<30) ORDER BY id LIMIT pageSize OFFSET pageOffset
            dataList = db.findAll(Selector.from(t.getClass())
                    .where("id" ,"<", 54)
                    .and(WhereBuilder.b("age", ">", 20).or("age", " < ", 30))
                    .orderBy("id")
                    .limit(pageSize)
                    .offset(pageSize * pageIndex));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }


    // 保存实体，返回是否保存成功
    public boolean saveSingleDataAfterDel(T t, Context context) {
        boolean result = false;
        try {
            DbUtils db = DbUtils.create(context, "myframe.db");
            db.configAllowTransaction(true);
            db.configDebug(true);
            db.deleteAll(t.getClass());
            result = db.saveBindingId(t);//保存对象关联数据库生成的id
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean saveSingleData(T t, Context context) {
        boolean result = false;
        try {
            DbUtils db = DbUtils.create(context, "myframe.db");
            db.configAllowTransaction(true);
            db.configDebug(true);

            result = db.saveBindingId(t);//保存对象关联数据库生成的id
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean saveListData(List<T> list, Context context) {
        boolean result = false;
        try {
            DbUtils db = DbUtils.create(context, "myframe.db");
            db.configAllowTransaction(true);
            db.configDebug(true);

            for(T t : list) {
                result = db.saveBindingId(t);//保存对象关联数据库生成的id
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean saveListDataAfterDel(List<T> list, Context context) {
        boolean result = false;
        try {
            DbUtils db = DbUtils.create(context, "myframe.db");
            db.configAllowTransaction(true);
            db.configDebug(true);

            if(list != null && list.size() > 0) {
                db.deleteAll(list.get(0).getClass());
            }
            for(T t : list) {
                result = db.saveBindingId(t);//保存对象关联数据库生成的id
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 根据id查找单个实体
    public T findById(T t, String id, Context context) {
        try {
            DbUtils db = DbUtils.create(context, "myframe.db");
            Object obj = db.findById(t.getClass(), id);
            t = (T)obj;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    // 查询第一条记录
    public T findFirst(T t, Context context) {
        try {
            DbUtils db = DbUtils.create(context, "myframe.db");
            Object obj = db.findFirst(Selector.from(t.getClass()).where("id","!=", null));
            t = (T)obj;

            /*Parent Parent = db.findFirst(Selector.from(Parent.class).where("name","=","test"));
            // IS NULL
            Parent Parent = db.findFirst(Selector.from(Parent.class).where("name","=", null));
            // IS NOT NULL
            Parent Parent = db.findFirst(Selector.from(Parent.class).where("name","!=", null));

            // op为"in"时，最后一个参数必须是数组或Iterable的实现类(例如List等)
            Parent test = db.findFirst(Selector.from(Parent.class).where("id", "in", new int[]{1, 2, 3}));
            // op为"between"时，最后一个参数必须是数组或Iterable的实现类(例如List等)
            Parent test = db.findFirst(Selector.from(Parent.class).where("id", "between", new String[]{"1", "5"}));

            DbModel dbModel = db.findDbModelAll(Selector.from(Parent.class).select("name"));//select("name")只取出name列
            List<DbModel> dbModels = db.findDbModelAll(Selector.from(Parent.class).groupBy("name").select("name", "count(name)"));

            //例：分组聚合查询出  Parent表中 非重复的name和它的对应数量
            List<DbModel> dbModels = db.findDbModelAll(Selector.form(Parent.class).select("distinct name,count(name) as num").groupBy("name"));
            // 执行自定义sql
            db.execNonQuery("sql")*/
        }catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
