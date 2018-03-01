package com.wf.xutils3.demo;

import android.util.Log;
import android.view.View;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * xUtils3.x数据库操作demo
 * Created by wangpf on 2017/5/5.
 */

public class xUtilsDataBaseDemo {
    private DbManager.DaoConfig daoConfig = null;
    private DbManager db = null;

    public xUtilsDataBaseDemo() {
    }

    //进行配置DaoConfig
    private void initDB() {
        daoConfig = new DbManager.DaoConfig()
                //设置数据库名，默认xutils.db
                .setDbName("myapp.db")
                //设置表创建的监听
                .setTableCreateListener(new DbManager.TableCreateListener() {
                    @Override
                    public void onTableCreated(DbManager db, TableEntity<?> table){
                        Log.i("JAVA", "onTableCreated：" + table.getName());
                    }
                })
                //设置是否允许事务，默认true
                //.setAllowTransaction(true)
                //设置数据库路径，默认安装程序路径下
                //.setDbDir(new File("/mnt/sdcard/"))
                //设置数据库的版本号
                //.setDbversion(1)
                //设置数据库更新的监听
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion,
                                          int newVersion) {
                    }
                })
                //设置数据库打开的监听
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        //开启数据库支持多线程操作，提升性能
                        db.getDatabase().enableWriteAheadLogging();
                    }
                });
        db = x.getDb(daoConfig);
    }

    //创建数据库
    private void createDB(View v) throws DbException {
        //用集合向child_info表中插入多条数据
        ArrayList<ChildInfo> childInfos = new ArrayList<>();
        childInfos.add(new ChildInfo("zhangsan"));
        childInfos.add(new ChildInfo("lisi"));
        childInfos.add(new ChildInfo("wangwu"));
        childInfos.add(new ChildInfo("zhaoliu"));
        childInfos.add(new ChildInfo("qianqi"));
        childInfos.add(new ChildInfo("sunba"));
        //db.save()方法不仅可以插入单个对象，还能插入集合
        db.save(childInfos);
    }

    //删除数据库
    private void delDB(View v) throws DbException {
        db.dropDb();
    }

    //删除表
    private void delTable(View v) throws DbException {
        db.dropTable(ChildInfo.class);
    }

    //查询表中的数据
    private void selelctDB(View v) throws DbException {
        //查询数据库表中第一条数据
        ChildInfo first = db.findFirst(ChildInfo.class);
        Log.i("JAVA",first.toString());
        //添加查询条件进行查询
        //第一种写法：
        WhereBuilder b = WhereBuilder.b();
        b.and("id",">",2); //构造修改的条件
        b.and("id","<",4);
        List<ChildInfo> all01 = db.selector(ChildInfo.class).where(b).findAll();//findAll()：查询所有结果
        for(ChildInfo childInfo :all01){
            Log.i("JAVA",childInfo.toString());
        }
        //第二种写法：
        List<ChildInfo> all02 = db.selector(ChildInfo.class).where("id",">",2).and("id","<",4).findAll();
        for(ChildInfo childInfo :all02){
            Log.i("JAVA",childInfo.toString());
        }
    }

    //修改表中的一条数据
    private void updateTable(View v) throws DbException {
        //第一种写法：
        ChildInfo first = db.findFirst(ChildInfo.class);
        first.setcName("zhansan2");
        db.update(first,"c_name"); //c_name：表中的字段名
        //第二种写法：
        WhereBuilder b = WhereBuilder.b();
        b.and("id","=",first.getId()); //构造修改的条件
        KeyValue name = new KeyValue("c_name","zhansan3");
        db.update(ChildInfo.class,b,name);
        //第三种写法：
        first.setcName("zhansan4");
        db.saveOrUpdate(first);
    }

    //删除表中的数据
    private void delTableData(View v) throws DbException {
        //第一种写法：
        db.delete(ChildInfo.class); //child_info表中数据将被全部删除
        //第二种写法，添加删除条件：
        WhereBuilder b = WhereBuilder.b();
        b.and("id",">",2); //构造修改的条件
        b.and("id","<",4);
        db.delete(ChildInfo.class, b);
    }
}
