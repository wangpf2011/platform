package com.lnint.frame.tab.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lnint.frame.tab.PagerSlidingTabStrip;
import com.lnint.frame.R;

public class FragmentMessage extends Fragment {

	private SubFragment1 subFragment1;
	private SubFragment2 subFragment2;
	private SubFragment3 subFragment3;
	/**
	 * PagerSlidingTabStrip的实例
	 */
	private PagerSlidingTabStrip tabs;

	/**
	 * 获取当前屏幕的密度
	 */
	private DisplayMetrics dm;

	@Override
	public void onCreate(Bundle savedInstanceState) {// 在前面执行

		super.onCreate(savedInstanceState);
		// 获取参数
		Bundle bundle = getArguments();
		if (null != bundle) {
			//
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_message, null);
		initView(view);

		return view;
	}

	private void initView(View view) {

		dm = getResources().getDisplayMetrics();
		ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
		tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
		pager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
		tabs.setViewPager(pager);
		setTabsValue();

	}

	/**
	 * 对PagerSlidingTabStrip的各项属性进行赋值。
	 */
	private void setTabsValue() {
		// 设置Tab是自动填充满屏幕的
		tabs.setShouldExpand(true);
		// 设置Tab的分割线是透明的
		tabs.setDividerColor(Color.TRANSPARENT);
		// tabs.setDividerColor(Color.BLACK);
		// 设置Tab底部线的高度
		tabs.setUnderlineHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 1, dm));
		// 设置Tab Indicator的高度
		tabs.setIndicatorHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, dm));// 4
		// 设置Tab标题文字的大小
		tabs.setTextSize((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, 16, dm)); // 16
		// 设置Tab Indicator的颜色
		tabs.setIndicatorColor(Color.parseColor("#45c01a"));// #45c01a
		// 设置选中Tab文字的颜色 (这是我自定义的一个方法)
		tabs.setSelectedTextColor(Color.parseColor("#45c01a"));// #45c01a
		// 取消点击Tab时的背景色
		tabs.setTabBackground(0);
	}

	// FragmentPagerAdapter FragmentStatePagerAdapter //不能用FragmentPagerAdapter


	public class MyPagerAdapter extends FragmentStatePagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		private final String[] titles = { "SubOne", "SubTwo", "SubThree" };

		@Override
		public CharSequence getPageTitle(int position) {
			return titles[position];
		}

		@Override
		public int getCount() {
			return titles.length;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
				case 0:
					if (null == subFragment1) {
						subFragment1 = new SubFragment1();
					}
					return subFragment1;
				case 1:
					if (null == subFragment2) {
						subFragment2 = new SubFragment2();
					}
					return subFragment2;
				case 2:
					if (null == subFragment3) {
						subFragment3 = new SubFragment3();
					}
					subFragment1 = new SubFragment1();
					return subFragment3;
				default:
					return null;
			}
		}

	}
}
