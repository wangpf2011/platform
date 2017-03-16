package com.wf.testcase;

import android.test.ApplicationTestCase;
import android.util.Log;

/**
 * Created by wangpf on 2017/3/16.
 */
public class ApplicationTest extends ApplicationTestCase<MainApplication> {
    public ApplicationTest() {
        super(MainApplication.class);
    }

    public void testStart() {
        String str = null;
        str = mContext.getResources().getString(R.string.app_name);
        Log.i("..",".............ApplicationTest...........app_name............."+str);
    }
}
