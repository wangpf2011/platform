package com.lnint.pattern.mvvm.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
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
import com.lnint.pattern.databinding.ActivityMvvmLoginBinding;
import com.lnint.pattern.mvvm.model.LoginNet;
import com.lnint.pattern.mvvm.model.RespInfo;
import com.lnint.pattern.mvvm.model.User;
import com.lnint.utils.StringUtils;

/**
 * 抽取控件到vm层
 * Created by wangpf on 2017/2/26.
 */
public class LoginMVVMActivity extends BaseActivity {
    private CustomProgressDialog dialog = null;
    private TextView title = null;

    private static final int MSG_SUCCESS = 0;
    private static final int MSG_ERROR = 1;
    private CustomHandler<LoginMVVMActivity> msgHandler = null;
    /**
     * Handler处理消息
     */
    private void initHandler() {
        msgHandler = new CustomHandler<LoginMVVMActivity>(this) {
            @Override
            public void handleMessage(Message msg) {
                LoginMVVMActivity outer = this.getReference().get();
                if(outer != null) {
                    switch(msg.what) {
                        case MSG_SUCCESS:
                            if(dialog != null) dialog.dismiss();
                            if(msg.obj != null) {
                                Toast.makeText(outer, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginMVVMActivity.this, SuccessMVVMActivity.class);
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

    private User user;
    private ActivityMvvmLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (ActivityMvvmLoginBinding)DataBindingUtil.setContentView(this, R.layout.activity_mvvm_login);
        user = new User("13000000000","111111");
        binding.setUser(user);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUsername(binding.edUsername.getText().toString());
                user.setPassword(binding.edPassword.getText().toString());

                if(!checkUser(user)) {
                    msgHandler.obtainMessage(MSG_ERROR, "用户名或密码为空").sendToTarget();
                    return;
                }

                dialog = CustomProgressDialog.createDialog(LoginMVVMActivity.this);
                dialog.setMessage("请稍后……");
                dialog.show();
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
        initHandler();
    }

    /**
     * 检查用户名、密码是否为空
     * @param user
     * @return
     */
    private boolean checkUser(User user) {
        if(StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return false;
        }
        return true;
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
