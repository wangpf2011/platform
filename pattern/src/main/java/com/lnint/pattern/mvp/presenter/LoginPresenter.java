package com.lnint.pattern.mvp.presenter;

import com.lnint.pattern.mvp.model.ILoginNet;
import com.lnint.pattern.mvp.model.LoginNet;
import com.lnint.pattern.mvp.model.RespInfo;
import com.lnint.pattern.mvp.model.User;
import com.lnint.pattern.mvp.view.ILoginView;
import com.lnint.utils.StringUtils;

/**
 * MVP的Presenter层，专门处理业务
 * Created by wangpf on 2017/3/4.
 */

public class LoginPresenter {
    private ILoginView view;
    private ILoginNet model;
    public LoginPresenter(ILoginView view) {
        this.view = view;
        this.model = new LoginNet();
    }

    /**
     * 用户登陆
     * @param user
     */
    public void login(final User user) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                RespInfo resp = model.appLogin(user);
                if("true".equals(resp.getSuccess())) {//登录OK
                    view.loginSuccess();
                }else {
                    view.loginFail(resp.getMessage());
                }
            }
        };
        thread.start();
    }

    /**
     * 检查用户名、密码是否为空
     * @param user
     * @return
     */
    public boolean checkUser(User user) {
        if(StringUtils.isEmpty(user.getUsername())
                || StringUtils.isEmpty(user.getPassword())) {
            return false;
        }
        return true;
    }
}
