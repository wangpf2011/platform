package com.lnint.common;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Window;
import com.lnint.utils.LogUtils;

/**
 * 应用程序Activity的基类
 * 
 * @author wangpf
 *
 * @date:2014年12月3日 下午2:15:43
 * @version : v1.0
 */
public class BaseActivity extends Activity {
	protected String className = getClass().getSimpleName();
	protected BaseActivity activity;

	@CallSuper
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature( Window.FEATURE_NO_TITLE );
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		if (Build.VERSION.SDK_INT >= 21) {
			setTheme(android.R.style.Theme_Material_Light_NoActionBar);
		} else if (Build.VERSION.SDK_INT >= 13) {
			setTheme(android.R.style.Theme_Holo_Light_NoActionBar);
		} else {
			setTheme(android.R.style.Theme_Light_NoTitleBar);
		}
		activity = this;

		//被系统回收后重启恢复
		if (savedInstanceState != null) {
			LogUtils.verbose("savedInstanceState is not null");
			onStateRestore(savedInstanceState);
		}

		// 添加Activity到栈中
		ActivityCollection.getInstance().addActivity(this);
	}

	protected void onStateRestore(@NonNull Bundle savedInstanceState) {

	}

	protected void setContentViewBefore() {
		LogUtils.verbose(className + " setContentView before");
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		LogUtils.verbose(className + " onBackPressed");
	}

	@CallSuper
	@Override
	public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
		super.onSaveInstanceState(outState, outPersistentState);
		LogUtils.verbose(className + " onSaveInstanceState");
	}

	@CallSuper
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		LogUtils.verbose(className + " onRestoreInstanceState");
	}

	@CallSuper
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		LogUtils.verbose(className + " onConfigurationChanged");
	}

	@CallSuper
	@Override
	public void onStop() {
		super.onStop();
		LogUtils.verbose(className + " onStop");
	}

	@CallSuper
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		LogUtils.verbose(className + " onSaveInstanceState");
	}

	@CallSuper
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		LogUtils.verbose(className + " onActivityResult");
	}

	@CallSuper
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		LogUtils.verbose(className + " onLowMemory");
		ActivityCollection.getInstance().removeActivity(this);
	}

	@Override
	protected void onDestroy() {
		//MainApplication.getInstance().removeActivity(this);
		//this.finish();
		super.onDestroy();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的如果是BACK，同时没有重复
			ActivityCollection.getInstance().removeActivity(this);
			this.finish();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}

	protected <T> T inflateView(@LayoutRes int layoutResource) {
		LogUtils.verbose(className + " inflate view by layout resource");
		//noinspection unchecked
		return (T) LayoutInflater.from(activity).inflate(layoutResource, null);
	}

	protected <T> T findView(@IdRes int id) {
		//noinspection unchecked
		return (T) findViewById(id);
	}
}
