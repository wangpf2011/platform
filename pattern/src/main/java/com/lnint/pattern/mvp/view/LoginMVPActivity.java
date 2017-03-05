package com.lnint.pattern.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lnint.common.ActivityCollection;
import com.lnint.common.BaseActivity;
import com.lnint.common.CustomHandler;
import com.lnint.common.progressdialog.CustomProgressDialog;
import com.lnint.pattern.R;
import com.lnint.pattern.mvp.model.User;
import com.lnint.pattern.mvp.presenter.LoginPresenter;

/**
 * MVP的View层，界面相关
 * Created by wangpf on 2017/2/26.
 */
public class LoginMVPActivity extends BaseActivity implements ILoginView {
    private CustomProgressDialog dialog = null;
    private TextView title = null;
    //用户名
    private EditText mUserName;
    //密码
    private EditText mPassword;
    private LoginPresenter presenter;

    private static final int MSG_SUCCESS = 0;
    private static final int MSG_ERROR = 1;
    private CustomHandler<LoginMVPActivity> msgHandler = null;
    /**
     * Handler处理消息
     */
    private void initHandler() {
        msgHandler = new CustomHandler<LoginMVPActivity>(this) {
            @Override
            public void handleMessage(Message msg) {
                LoginMVPActivity outer = this.getReference().get();
                if(outer != null) {
                    switch(msg.what) {
                        case MSG_SUCCESS:
                            if(dialog != null) dialog.dismiss();
                            if(msg.obj != null) {
                                Toast.makeText(outer, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginMVPActivity.this, SuccessMVPActivity.class);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);
        title = (TextView) this.findViewById(R.id.txt_title);
        title.setText("用户登陆");
        mUserName = (EditText) this.findViewById(R.id.ed_username);
        mPassword = (EditText) this.findViewById(R.id.ed_password);
        presenter = new LoginPresenter(this);
        initHandler();

        Button login = (Button) this.findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User();
                user.setUsername(mUserName.getText().toString());
                user.setPassword(mPassword.getText().toString());
                if(!presenter.checkUser(user)) {
                    msgHandler.obtainMessage(MSG_ERROR, "用户名或密码为空").sendToTarget();
                    return;
                }

                dialog = CustomProgressDialog.createDialog(LoginMVPActivity.this);
                dialog.setMessage("请稍后……");
                dialog.show();
                presenter.login(user);
            }
        });
    }

    /**
     * 登陆成功
     */
    public void loginSuccess() {
        msgHandler.obtainMessage(MSG_SUCCESS, "登陆成功").sendToTarget();
    }

    /**
     * 登陆失败
     * @param message
     */
    public void loginFail(String message) {
        msgHandler.obtainMessage(MSG_ERROR, message).sendToTarget();
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
