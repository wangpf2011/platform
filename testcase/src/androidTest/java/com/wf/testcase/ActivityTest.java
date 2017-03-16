package com.wf.testcase;

import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

/**
 * Created by wangpf on 2017/3/16.
 */

public class ActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Context ctx;

    public ActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ctx = getActivity().getApplicationContext();
    }

    public void testStart() {
        Intent intent = new Intent(ctx, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
        Log.i("TestActivity","................startActivity............................");
    }
}
