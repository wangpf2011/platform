package com.wf.testcase;

import android.test.InstrumentationTestCase;

/**
 * Created by wangpf on 2017/3/16.
 */

public class TestSubject extends InstrumentationTestCase {
    private static final String LOG_TAG = "test";

    public void testPublishSubject() {
        launchActivity("com.wf.testcase",LoginActivity.class,null);
    }
}
