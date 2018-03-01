package com.wf.xutils3.utils;

import com.wf.xutils3.MyApplication;
import java.util.ArrayList;
import java.util.List;
import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

/**
 * 数据库操作工具类
 * Created by wangpf on 2016/11/8.
 */

public class xUtilsDataUtils<T> {
    // 查询所有对象
    public List<UserEntity> findAllData() {
        List<UserEntity> dataList = new ArrayList<UserEntity>();
        try {
            DbManager db = x.getDb(MyApplication.getInstance().getDaoConfig());
            //db.configAllowTransaction(true);
            //db.configDebug(true);
            //db.deleteAll(TradeRecEntity.class);
            //数据结果集
            dataList = db.selector(UserEntity.class).findAll();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    // 根据条件查询对象集合
    public List<UserEntity> findDataList() {
        int pageSize = 10;
        int pageIndex = 0;
        List<UserEntity> dataList = new ArrayList<UserEntity>();
        try {
            DbManager db = x.getDb(MyApplication.getInstance().getDaoConfig());
            // WHERE id<54 AND (age>20 OR age<30) ORDER BY id LIMIT pageSize OFFSET pageOffset
            dataList = db.selector(UserEntity.class)
                    .where("id", "!=", null)
                    .and(WhereBuilder.b("age", ">", 20).or("age", " < ", 30))
                    .orderBy("id")
                    .limit(pageSize)
                    .offset(pageSize * pageIndex).findAll();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }


    // 保存实体，返回是否保存成功
    public boolean saveSingleDataAfterDel(T t) {
        boolean result = false;
        try {
            DbManager db = x.getDb(MyApplication.getInstance().getDaoConfig());
            db.delete(t.getClass());
            result = db.saveBindingId(t);//保存对象关联数据库生成的id
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean saveSingleData(T t) {
        boolean result = false;
        try {
            DbManager db = x.getDb(MyApplication.getInstance().getDaoConfig());
            result = db.saveBindingId(t);
        }catch (DbException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean saveListData(List<T> list) {
        boolean result = false;
        try {
            DbManager db = x.getDb(MyApplication.getInstance().getDaoConfig());
            for(T t : list) {
                result = db.saveBindingId(t);//保存对象关联数据库生成的id
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean saveListDataAfterDel(List<T> list) {
        boolean result = false;
        try {
            DbManager db = x.getDb(MyApplication.getInstance().getDaoConfig());
            if(list != null && list.size() > 0) {
                db.delete(list.get(0).getClass());
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
    public T findById(T t, String id) {
        try {
            DbManager db = x.getDb(MyApplication.getInstance().getDaoConfig());
            Object obj = db.findById(t.getClass(), id);
            t = (T)obj;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    // 查询第一条记录
    public T findFirst(T t) {
        try {
            DbManager db = x.getDb(MyApplication.getInstance().getDaoConfig());
            Object obj = db.selector(t.getClass()).where("id", "!=", null).findFirst();
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
