package com.wf.xutils3;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * xUtils3.3应用demo
 * Created by wangpf on 2016/7/9.
 */
public class MyApplication extends Application {
    private DbManager.DaoConfig daoConfig;
    private static MyApplication mInstance = null;

    public DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

        daoConfig = new DbManager.DaoConfig()
                .setDbName("lyj_db")//创建数据库的名称
                .setDbVersion(1)//数据库版本号
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                    }
                });//数据库更新操作
    }

    public static MyApplication getInstance() {
        return mInstance;
    }
}
