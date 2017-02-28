package com.lnint.pattern.mvc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lnint.common.BaseActivity;
import com.lnint.pattern.R;

/**
 * MVC的Control层
 * Created by wangpf on 2017/2/26.
 */
public class LoginActivity extends BaseActivity {
    //用户名
    private EditText mUserName;
    //密码
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);
        mUserName = (EditText) this.findViewById(R.id.ed_username);
        mPassword = (EditText) this.findViewById(R.id.ed_username);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
