package com.wf.andos.tab;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wf.andos.R;
import com.wf.andos.tab.fragment.FragmentHome;
import com.wf.andos.tab.fragment.FragmentMessage;
import com.wf.andos.tab.fragment.FragmentMy;
import com.wf.core.ActivityCollection;

public class MainTabActivity extends FragmentActivity {
	private String TAG = MainTabActivity.class.getName();
	private long exitTime = 0;

	public TabFragmentHost mTabHost;
	// 标签
	private String[] TabTag = { "tab1", "tab2", "tab3" };
	// 自定义tab布局显示文本和顶部的图片
	private Integer[] ImgTab = { R.layout.tab_main_home,
			R.layout.tab_main_message, R.layout.tab_main_my };

	// tab 选中的activity
	private Class[] ClassTab = { FragmentHome.class, FragmentMessage.class,
			FragmentMy.class };

	// tab选中背景 drawable 样式图片 背景都是同一个,背景颜色都是 白色。。。
	private Integer[] StyleTab = { R.color.white, R.color.white, R.color.white};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_tabs);
		setupView();
		initValue();

		setLinstener();
		fillData();
	}

	private void setupView() {
		// 实例化framentTabHost
		mTabHost = (TabFragmentHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(),
				android.R.id.tabcontent);
	}

	private void initValue() {
		// 初始化tab选项卡
		initTabView();
	}

	private void setLinstener() {
		// imv_back.setOnClickListener(this);
	}

	private void fillData() {
	}

	// 初始化 tab 自定义的选项卡
	private void initTabView() {

		// 可以传递参数 b;传递公共的userid,version,sid
		Bundle b = new Bundle();
		// 循环加入自定义的tab
		for (int i = 0; i < TabTag.length; i++) {
			// 封装的自定义的tab的样式
			View indicator = getIndicatorView(i);
			mTabHost.addTab(
					mTabHost.newTabSpec(TabTag[i]).setIndicator(indicator),
					ClassTab[i], b);// 传递公共参数

		}
		mTabHost.getTabWidget().setDividerDrawable(R.color.white);
	}

	// 设置tab自定义样式:注意 每一个tab xml子布局的linearlayout 的id必须一样
	private View getIndicatorView(int i) {
		// 找到tabhost的子tab的布局视图
		View v = getLayoutInflater().inflate(ImgTab[i], null);
		LinearLayout tv_lay = (LinearLayout) v.findViewById(R.id.layout_back);
		tv_lay.setBackgroundResource(StyleTab[i]);

		return v;
	}

	//退出按钮
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN ) {
			if((System.currentTimeMillis()-exitTime) > 2000) {
				Toast.makeText(MainTabActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			}else {
				ActivityCollection.getInstance().exit();
			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}
}
