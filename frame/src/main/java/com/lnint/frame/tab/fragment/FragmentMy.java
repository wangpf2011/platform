package com.lnint.frame.tab.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lnint.androidpicker.MainPickerActivity;
import com.lnint.citypicker.CityPickerActivity;
import com.lnint.frame.R;

public class FragmentMy extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_my, null);
		//选择城市
		Button choiseCity = (Button)view.findViewById(R.id.btn_choise_city);
		choiseCity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), CityPickerActivity.class);
				startActivity(intent);
			}
		});

		//设计模式
		/*Button pattern = (Button)view.findViewById(R.id.btn_pattern);
		pattern.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), PatternMainActivity.class);
				startActivity(intent);
			}
		});*/

		//选择器示例
		Button picker = (Button)view.findViewById(R.id.btn_picker);
		picker.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), MainPickerActivity.class);
				startActivity(intent);
			}
		});

		return view;
	}
}
