package com.lnint.pattern;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lnint.common.ActivityCollection;
import com.lnint.common.BaseActivity;
import com.lnint.pattern.mvc.LoginActivity;

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
        Intent intent = new Intent(PatternMainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * MVP模式
     * @param view
     */
    public void patternMVP(View view) {

    }

    /**
     * MVVM模式
     * @param view
     */
    public void patternMVVM(View view) {

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
