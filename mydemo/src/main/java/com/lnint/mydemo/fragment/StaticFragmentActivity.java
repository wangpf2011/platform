package com.lnint.mydemo.fragment;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.lnint.mydemo.R;

/**
 * 静态方式实现Fragment
 * Created by wangpf on 2017/2/9.
 */

public class StaticFragmentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.demo_fragment_static);
    }
}
