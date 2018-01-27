package com.lnint.andos.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 系统service，定时发送心跳帧
 * Created by wangpf on 2016/7/23.
 */
public class SystemService extends Service {

    private Thread heartThread = null;
    private boolean running = true;

    //其他对象通过bindService方法通知该Service时该方法会被调用
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //其他对象通过unbindService方法通知该Service时该方法会被调用
    @Override
    public boolean onUnbind(Intent intent){
        return false;
    }

    //该服务不存在需要被创建时被调用，不管startService()还是bindService()都会在启动时调用该方法
    @Override
    public void onCreate() {

    }

    //用startService方法调用该服务时，在onCreate()方法调用之后，会调用改方法
    @Override
    public void onStart(Intent intent,int startid) {
        Log.i("bluetooth", "heartThread.isAlive()="+(heartThread==null?"null":heartThread.isAlive()));
        if(heartThread != null && heartThread.isAlive()) return;

        running = true;
        heartThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(running) {
                    try {
                        Log.i("bluetooth", "heartThread=发送心跳");
                        Thread.sleep(20000);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        heartThread.start();
    }

    //该服务被销毁时调用该方法
    @Override
    public void onDestroy() {
        try {
            running = false;
            if(heartThread != null) {
                heartThread.interrupt();
                heartThread = null;
            }
            Log.i("bluetooth", "heartThread.isStop()="+(heartThread==null?"null":heartThread.isAlive()));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
