package com.wf.xutils3.demo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wf.xutils3.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * xUtils3.xFragment注解使用demo
 * Created by wangpf on 2017/5/5.
 */
@ContentView(R.layout.activity_main)
public class xUtilsFragmentDemo extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
    }
}
