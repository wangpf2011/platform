package com.lnint.frame.tab.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SubFragment2 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Toast.makeText(getActivity(), "SubFragment2==onCreateView", Toast.LENGTH_LONG).show();

		TextView tv = new TextView(getActivity());
		tv.setTextSize(25);
		tv.setBackgroundColor(Color.parseColor("#FFA07A"));
		tv.setText("SubFragment2");
		tv.setGravity(Gravity.CENTER);
		return tv;
	}
}
