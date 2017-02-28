package com.lnint.common;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * 保存Activity对象--单例
 * Created by wangpf on 2017/2/28.
 */

public class ActivityCollection {
    // 定义一个私有构造方法
    private ActivityCollection() {

    }
    //定义一个静态私有变量
    //(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
    private static volatile ActivityCollection instance;

    //定义一个共有的静态方法，返回该类型实例
    public static ActivityCollection getInstance() {
        // 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
        if (instance == null) {
            //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
            synchronized (ActivityCollection.class) {
                //未初始化，则初始instance变量
                if (instance == null) {
                    instance = new ActivityCollection();
                }
            }
        }
        return instance;
    }

    /**
     * 保存Activity，退出应用及返回时使用
     */
    public List<Activity> mList = new LinkedList<Activity>();

    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void removeActivity(Activity activity) {
        mList.remove(activity);
    }

    public void exit() {
        try {
            //finish所有Activity
            for(Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            System.exit(0);
        }
    }
}
