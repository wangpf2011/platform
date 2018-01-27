package com.lnint.example.sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.lnint.example.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Demo--数据库操作
 * Created by wangpf on 2017/2/10.
 */
//@ContentView(R.layout.demo_sqlite)
public class SQLiteActivity extends Activity {
    private SimpleAdapter adapter;
    private ArrayList<HashMap<String, Object>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ViewUtils.inject(this); //注入view和事件
        this.setContentView(R.layout.demo_sqlite);

        ListView listView = (ListView) findViewById(R.id.ll_data);
        dataList = new ArrayList<HashMap<String, Object>>();
        adapter = new SimpleAdapter(this, dataList, R.layout.demo_sqlite_item,
                new String[]{"id","name","value","type"}, new int[]{R.id.txt_item_id,R.id.txt_item_name,R.id.txt_item_value,R.id.txt_item_type});
        listView.setAdapter(adapter);
    }

    public void saveData(View view) {
        DBUtils<SystemDictEntity> dbUtils = new DBUtils<SystemDictEntity>();
        SystemDictEntity entity = new SystemDictEntity();
        entity.setName("个人充电站");
        entity.setValue("01");
        entity.setType("type");
        dbUtils.saveSingleData(entity, this);
    }

    public void findData(View view) {
        dataList.clear();

        DBUtils<SystemDictEntity> dbUtils = new DBUtils<SystemDictEntity>();
        List<SystemDictEntity> list = dbUtils.findAllData(new SystemDictEntity(), this);
        for(SystemDictEntity entity : list) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", entity.getId());
            map.put("name", entity.getName());
            map.put("value", entity.getValue());
            map.put("type", entity.getType());
            dataList.add(map);
        }
        adapter.notifyDataSetChanged();
    }
}
