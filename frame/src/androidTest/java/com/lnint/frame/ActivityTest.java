package com.lnint.frame;

import android.test.ActivityUnitTestCase;

import com.lnint.frame.tab.MainTabActivity;

/**
 * ActivityUnitTestCase——对单个Activity进行单一测试的类。
 * 使用它，你可以注入模拟的Context或Application，或者两者。
 * 它用于对Activity进行单元测试。也就是说你可以用于测试单独的activity ，
 * 虽然也需要利用模拟机或真机启动，但你启动的只是你需要做测试的activity，于其他activity无关。
 *
 * Created by wangpf on 2017/4/23.
 */

public class ActivityTest extends ActivityUnitTestCase<MainTabActivity> {
    public ActivityTest() {
        super(MainTabActivity.class);
    }
}
