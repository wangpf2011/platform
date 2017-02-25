package com.lnint.frame.tab.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.lnint.citypicker.CityPickerActivity;
import com.lnint.frame.R;

public class FragmentMy extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_my, null);
		Button choiseCity = (Button)view.findViewById(R.id.btn_choise_city);
		choiseCity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), CityPickerActivity.class);
				startActivity(intent);
			}
		});

		Toast.makeText(getActivity(), "FragmentMy==onCreateView", Toast.LENGTH_LONG).show();

		return view;
	}
}
