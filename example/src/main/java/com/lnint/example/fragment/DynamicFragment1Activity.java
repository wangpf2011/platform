package com.lnint.example.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.lnint.example.R;

/**
 * 动态方式使用Fragment
 *
 * 填充到android.R.id.Content
 * Created by wangpf on 2017/2/9.
 */

public class DynamicFragment1Activity extends Activity {
    private DynamicFragment1 fragment1;
    private DynamicFragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.demo_fragment_dynamic1);
        initFragment();
    }

    //Button1
    public void clickButton1(View view) {
        this.getFragmentManager().beginTransaction().show(fragment1).hide(fragment2).commit();
    }

    //Button2
    public void clickButton2(View view) {
        this.getFragmentManager().beginTransaction().show(fragment2).hide(fragment1).commit();
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        fragment1 = new DynamicFragment1();
        fragment2 = new DynamicFragment2();

        // 为布局添加fragment,开启事物
        FragmentManager fm = this.getFragmentManager();
        FragmentTransaction tran = fm.beginTransaction();
        //R.id.relative为布局
        tran.add(android.R.id.content, fragment1, "fragment1").show(fragment1)
                .add(android.R.id.content, fragment2, "fragment2").hide(fragment2);
        tran.commit();
    }
}
