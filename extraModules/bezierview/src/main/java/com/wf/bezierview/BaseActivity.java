package com.wf.bezierview;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.Window;

/**
 * 应用程序Activity的基类
 *
 * Created by wangpf
 * Created at 2018/9/17 15:20
 */
public class BaseActivity extends Activity {
	protected String pageName = "BaseActivity";

	@CallSuper
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE );
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		/*if (Build.VERSION.SDK_INT >= 21) {
			setTheme(android.R.style.Theme_Material_Light_NoActionBar);
		} else if (Build.VERSION.SDK_INT >= 13) {
			setTheme(android.R.style.Theme_Holo_Light_NoActionBar);
		} else {
			setTheme(android.R.style.Theme_Light_NoTitleBar);
		}*/

		//被系统回收后重启恢复
		if (savedInstanceState != null) {
			onStateRestore(savedInstanceState);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	protected void onStateRestore(@NonNull Bundle savedInstanceState) {
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@CallSuper
	@Override
	public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
		super.onSaveInstanceState(outState, outPersistentState);
	}

	@CallSuper
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	@CallSuper
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@CallSuper
	@Override
	protected void onStop() {
		super.onStop();
	}

	@CallSuper
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@CallSuper
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@CallSuper
	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	protected void onDestroy() {
		//ActivityCollection.getInstance().removeActivity(this);
		//this.finish();
		super.onDestroy();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的如果是BACK，同时没有重复
			this.finish();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}

}
