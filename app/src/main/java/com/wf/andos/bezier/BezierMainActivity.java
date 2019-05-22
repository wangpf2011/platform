package com.wf.andos.bezier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wf.bezierview.base.CustomViewActivity;
import com.wf.bezierview.base.ViewStubActivity;
import com.wf.bezierview.BezierCubicActivity;
import com.wf.bezierview.BezierQuadActivity;
import com.wf.bezierview.ChargeMonitorActivity;
import com.wf.bezierview.PageViewActivity;
import com.wf.bezierview.WaveViewActivity;

public class BezierMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bezier_main);
    }

    //自定义View
    public void customView(View view) {
        Intent intent  = new Intent(BezierMainActivity.this, CustomViewActivity.class);
        startActivity(intent);
    }

    //二次曲线
    public void bezierQuad(View view) {
        Intent intent  = new Intent(BezierMainActivity.this, BezierQuadActivity.class);
        startActivity(intent);
    }

    //三次曲线
    public void bezierCubic(View view) {
        Intent intent  = new Intent(BezierMainActivity.this, BezierCubicActivity.class);
        startActivity(intent);
    }

    //水纹波动
    public void waveView(View view) {
        Intent intent  = new Intent(BezierMainActivity.this, WaveViewActivity.class);
        startActivity(intent);
    }

    //车辆充电效果
    public void chargeMonitor(View view) {
        Intent intent  = new Intent(BezierMainActivity.this, ChargeMonitorActivity.class);
        startActivity(intent);
    }

    //翻页效果
    public void pageView(View view) {
        Intent intent  = new Intent(BezierMainActivity.this, PageViewActivity.class);
        startActivity(intent);
    }

    //界面优化ViewStub
    public void viewStub(View view) {
        Intent intent  = new Intent(BezierMainActivity.this, ViewStubActivity.class);
        startActivity(intent);
    }
}
