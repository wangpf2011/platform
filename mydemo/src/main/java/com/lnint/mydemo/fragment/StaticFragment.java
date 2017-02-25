package com.lnint.mydemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lnint.mydemo.R;

/**
 * Fragment1
 * Created by wangpf on 2017/2/9.
 */

public class StaticFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.demo_fragment1,container,false);
        TextView textView=(TextView)view.findViewById(R.id.textView);
        textView.setText("静态加载Fragment");
        return view;
    }
}
