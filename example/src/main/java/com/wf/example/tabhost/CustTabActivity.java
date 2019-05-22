package com.wf.example.tabhost;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.wf.example.R;

/**
 * TabHost：TabActivity
 *
 * Created by wangpf on 2018/1/27.
 */

public class CustTabActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_tabhost);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec page1 = tabHost.newTabSpec("tab1")
                .setIndicator("选项1")
                .setContent(R.id.tab1);
        tabHost.addTab(page1);

        TabHost.TabSpec page2 = tabHost.newTabSpec("tab2")
                .setIndicator("选项2")
                .setContent(R.id.tab2);
        tabHost.addTab(page2);

        TabHost.TabSpec page3 = tabHost.newTabSpec("tab3")
                .setIndicator("选项3")
                .setContent(R.id.tab3);
        tabHost.addTab(page3);
    }
}
