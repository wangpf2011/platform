package com.lnint.pattern.mvvm.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lnint.common.ActivityCollection;
import com.lnint.common.BaseActivity;
import com.lnint.pattern.R;

/**
 * 登陆成功显示页面
 * Created by wangpf on 2017/2/28.
 */

public class SuccessMVVMActivity extends BaseActivity {
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_success);
        title = (TextView) this.findViewById(R.id.txt_title);
        title.setText("登陆成功");
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
