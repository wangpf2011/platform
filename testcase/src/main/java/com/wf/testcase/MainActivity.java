package com.wf.testcase;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by wangpf on 2017/3/16.
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        System.out.println("...............MainActivity......onCreate............");
        Log.i("MainActivity","................onCreate............................");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("...............MainActivity......onStart............");
        Log.i("MainActivity","................onStart............................");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("...............MainActivity......onStop............");
        Log.i("MainActivity","................onStop............................");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("...............MainActivity......onDestroy............");
        Log.i("MainActivity","................onDestroy............................");
    }
}
