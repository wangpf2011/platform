package com.lnint.andos.tab.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lnint.andos.R;
import com.lnint.example.tabhost.CustTabActivity;

public class FragmentHome extends Fragment implements View.OnClickListener{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, null );
		//TabHost
		view.findViewById(R.id.btn_tabhost).setOnClickListener(this);
		return view;
	}

	//点击事件
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_tabhost:
				Intent intent = new Intent(getActivity(), CustTabActivity.class);
				startActivity(intent);
				break;
		}
	}
}
