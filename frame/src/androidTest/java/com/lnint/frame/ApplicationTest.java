package com.lnint.frame;

import android.test.ApplicationTestCase;
import android.util.Log;

/**
 * ApplicationTestCase——测试整个应用程序的类。它允许你注入一个模拟的Context到应用程序中，
 * 在应用程序启动之前初始化测试参数，并在应用程序结束之后销毁之前检查应用程序。
 *
 * 使用Context，你可以浏览资源，文件，数据库等等。
 * 基类是AndroidTestCase，一般常见的是它的子类，和特定组件关联。
 * Created by wangpf on 2017/4/23.
 */

public class ApplicationTest extends ApplicationTestCase<MainApplication> {
    public ApplicationTest() {
        super(MainApplication.class);
    }

    public void testStart() {
        String str = null;
        str = mContext.getResources().getString(R.string.app_name);
        Log.i("..", ".............ApplicationTest ...........app_name............." + str);
    }
}
