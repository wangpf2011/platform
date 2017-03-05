package com.lnint.pattern.mvvm.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.lnint.pattern.BR;

/**
 * 用户实体
 * Created by wangpf on 2017/2/26.
 */

public class User extends BaseObservable {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public int checkUserValidity() {
        if (username == null || password == null ||
                username.isEmpty() ||
                password.isEmpty()) {
            return -1;
        }
        return 0;
    }
}
