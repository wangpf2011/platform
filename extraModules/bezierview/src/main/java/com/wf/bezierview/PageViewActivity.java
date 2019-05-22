package com.wf.bezierview;

import android.os.Bundle;

/**
 * 贝塞尔水纹波动效果
 *
 * Created by wangpf
 * Created at 2018/9/17 17:20
 */
public class PageViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bezier_pageview);
        pageName = "PageViewActivity";
    }
}
