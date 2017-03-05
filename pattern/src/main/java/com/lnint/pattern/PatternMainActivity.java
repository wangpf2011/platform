package com.lnint.pattern;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lnint.common.ActivityCollection;
import com.lnint.common.BaseActivity;
import com.lnint.pattern.mvp.view.LoginMVPActivity;
import com.lnint.pattern.mvvm.view.LoginMVVMActivity;

/**
 * 模式Demo主界面
 * Created by wangpf on 2017/2/26.
 */
public class PatternMainActivity extends BaseActivity {
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        title = (TextView) this.findViewById(R.id.txt_title);
        title.setText("设计模式");
    }

    /**
     * MVC模式
     * @param view
     */
    public void patternMVC(View view) {
        Intent intent = new Intent(PatternMainActivity.this, com.lnint.pattern.mvc.LoginActivity.class);
        startActivity(intent);
    }

    /**
     * MVP模式
     * @param view
     */
    public void patternMVP(View view) {
        Intent intent = new Intent(PatternMainActivity.this, LoginMVPActivity.class);
        startActivity(intent);
    }

    /**
     * MVVM模式
     * @param view
     */
    public void patternMVVM(View view) {
        Intent intent = new Intent(PatternMainActivity.this, LoginMVVMActivity.class);
        startActivity(intent);
    }

    /**
     * 返回
     * @param view
     */
    public void backBtn(View view) {
        ActivityCollection.getInstance().removeActivity(this);
        this.finish();
    }
}
