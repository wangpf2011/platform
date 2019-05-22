package com.wf.bezierview.base;

import android.os.Bundle;

import com.wf.bezierview.BaseActivity;
import com.wf.bezierview.R;

/**
 * 自定义View
 * Created by wang on 2019/2/28.
 */

public class CustomViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bezier_customview);
        pageName = "CustomViewActivity";
    }
}
