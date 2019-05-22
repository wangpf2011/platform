package com.wf.bezierview;

import android.os.Bundle;

/**
 * 电动汽车充电监控界面
 * Created by wang on 2019/3/1.
 */
public class ChargeMonitorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bezier_chargemonitor);
        pageName = "ChargeMonitorActivity";

        ChargeMonitorView waveView1 = (ChargeMonitorView)findViewById(R.id.waveView1);
        waveView1.waveAnimation();
        ChargeMonitorView waveView2 = (ChargeMonitorView)findViewById(R.id.waveView2);
        waveView2.waveAnimation();
    }
}
