package com.wf.bezierview;

import android.os.Bundle;

/**
 * 三次贝塞尔曲线，拖动效果
 * Created by wangpf
 * Created at 2019/2/26 21:33
 */
public class BezierQuadActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bezier_bezierquad);
        pageName = "BezierQuadActivity";
    }
}
