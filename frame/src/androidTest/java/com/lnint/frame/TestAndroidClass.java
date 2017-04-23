package com.lnint.frame;

import android.test.InstrumentationTestCase;

import com.lnint.frame.tab.MainTabActivity;

/**
 * Instrumentation和Activity有点类似，只不过Activity是需要一个界面的，
 * 而Instrumentation并不是这样的，我们可以将它理解为一种没有图形界面的，
 * 具有启动能力的，用于监控其他类(用Target Package声明)的工具类。
 *
 * 方法名以test打头，testMainTab，并不需要@Test注解
 * Created by wangpf on 2017/4/23.
 */

public class TestAndroidClass extends InstrumentationTestCase {
    private static final String TAG = "TestAndroidClass";

    public void test() throws Exception {
        assertEquals(0, 0);
    }

    public void testMainTab() {
        launchActivity("com.lnint.frame.tab", MainTabActivity.class, null);
    }
}
