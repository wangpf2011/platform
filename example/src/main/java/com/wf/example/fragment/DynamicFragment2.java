package com.wf.example.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wf.example.R;

/**
 * Fragment1
 * Created by wangpf on 2017/2/9.
 */

public class DynamicFragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.demo_fragment2,container,false);
        TextView textView=(TextView)view.findViewById(R.id.textView);
        textView.setText("动态加载Fragment2");
        return view;
    }
}
