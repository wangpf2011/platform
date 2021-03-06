package com.wf.andos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.wf.core.BaseActivity;

/**
 * 欢迎界面
 * @author wangpf
 * 
 * @date:2014年11月21日 上午11:27:52
 * @version : v1.0
 */
public class StartActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.start, null);
		this.setContentView(view);
		
		//渐变展示启动屏
		AlphaAnimation aa = new AlphaAnimation(0.3f,1.0f);
		aa.setDuration(2000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				initial();
			}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			@Override
			public void onAnimationStart(Animation animation) {}
		});
	}
	
	private void initial() {
		Intent intent = new Intent();
		intent.setClass(StartActivity.this, MainTabActivity.class);
		//intent.setClass(StartActivity.this, SQLiteActivity.class);
		startActivity(intent);
		this.finish();
	}
}
