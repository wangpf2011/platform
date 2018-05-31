package com.wf.retrofit;

import android.app.Application;

public class RxJavaApplication extends Application {
    private static RxJavaApplication INSTANCE;

    public static RxJavaApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
