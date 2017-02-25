package com.lnint.mydemo.gridview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lnint.mydemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demo--GridView
 * Created by wangpf on 2017/2/13.
 */

public class GridViewActivity extends Activity {
    private GridView gridview;
    private GridViewAdpter adpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.demo_gridview);

        gridview = (GridView)findViewById(R.id.gv_station_type);
        List<Map<String, String>> items = new ArrayList<Map<String, String>>();
        String[] stationType = this.getResources().getStringArray(R.array.station_type);
        for(int i=0; i<stationType.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("value", stationType[i].split("_")[0]);
            map.put("name", stationType[i].split("_")[1]);
            items.add(map);
        }
        adpter = new GridViewAdpter(items, GridViewActivity.this);
        gridview.setAdapter(adpter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adpter.chiceState(position);
            }
        });
    }
}
