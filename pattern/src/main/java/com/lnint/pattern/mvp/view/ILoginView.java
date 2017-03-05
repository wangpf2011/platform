package com.lnint.pattern.mvp.view;

/**
 * MVP的View层，界面相关 -- 接口
 * Created by wangpf on 2017/3/4.
 */

public interface ILoginView {
    public void loginSuccess();

    public void loginFail(String message);
}
