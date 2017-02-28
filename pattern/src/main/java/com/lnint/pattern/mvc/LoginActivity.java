package com.lnint.pattern.mvc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lnint.common.ActivityCollection;
import com.lnint.common.AppHelper;
import com.lnint.common.BaseActivity;
import com.lnint.common.CustomHandler;
import com.lnint.common.progressdialog.CustomProgressDialog;
import com.lnint.pattern.R;
import com.lnint.pattern.entity.RespInfo;
import com.lnint.pattern.entity.User;
import com.lnint.utils.StringUtils;

/**
 * MVC的Control层
 * Created by wangpf on 2017/2/26.
 */
public class LoginActivity extends BaseActivity {
    private CustomProgressDialog dialog = null;
    private TextView title = null;
    //用户名
    private EditText mUserName;
    //密码
    private EditText mPassword;

    private static final int MSG_SUCCESS = 0;
    private static final int MSG_ERROR = 1;
    private CustomHandler<LoginActivity> msgHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);this.setContentView(R.layout.activity_login);
        title = (TextView) this.findViewById(R.id.txt_title);
        title.setText("用户登陆");
        mUserName = (EditText) this.findViewById(R.id.ed_username);
        mPassword = (EditText) this.findViewById(R.id.ed_password);
        initHandler();

        Button login = (Button) this.findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StringUtils.isEmpty(mUserName.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "请填写用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(StringUtils.isEmpty(mPassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "请填写密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                dialog = CustomProgressDialog.createDialog(LoginActivity.this);
                dialog.setMessage("请稍后……");
                dialog.show();
                final User user = new User();
                user.setUsername(mUserName.getText().toString());
                user.setPassword(mPassword.getText().toString());
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        LoginNet loginNet = new LoginNet();
                        RespInfo resp = loginNet.appLogin(user);
                        if("true".equals(resp.getSuccess())) {//登录OK
                            msgHandler.obtainMessage(MSG_SUCCESS, "登陆成功").sendToTarget();
                        }else {
                            msgHandler.obtainMessage(MSG_ERROR, resp.getMessage()).sendToTarget();
                        }
                    }
                };
                thread.start();
            }
        });
    }

    /**
     * Handler处理消息
     */
    private void initHandler() {
        msgHandler = new CustomHandler<LoginActivity>(this) {
            @Override
            public void handleMessage(Message msg) {
                LoginActivity outer = this.getReference().get();
                if(outer != null) {
                    switch(msg.what) {
                        case MSG_SUCCESS:
                            if(dialog != null) dialog.dismiss();
                            if(msg.obj != null) {
                                Toast.makeText(outer, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, SuccessActivity.class);
                                startActivity(intent);
                            }
                            break;
                        case MSG_ERROR:
                            if(dialog != null) dialog.dismiss();
                            if(msg.obj != null) {
                                Toast.makeText(outer, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
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
