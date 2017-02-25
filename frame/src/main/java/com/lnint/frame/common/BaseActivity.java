package com.lnint.frame.common;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;

import com.lnint.frame.MainApplication;

/**
 * 应用程序Activity的基类
 * 
 * @author wangpf
 *
 * @date:2014年12月3日 下午2:15:43
 * @version : v1.0
 */
public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature( Window.FEATURE_NO_TITLE );
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// 添加Activity到栈中
		MainApplication.getInstance().addActivity(this);
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
	    	MainApplication.getInstance().removeActivity(this);
			this.finish();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
