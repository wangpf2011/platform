package com.wf.testcase;

import android.app.Application;
import android.util.Log;

/**
 * Created by wangpf on 2017/3/16.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String app_name = getResources().getString(R.string.app_name);
        Log.i("MyApp",".........MyApp....app_name.........."+app_name);
    }
}
