package com.wf.xutils3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.wf.xutils3.bean.SystemDictEntity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 数据库操作 --插入、查询
 * Created by wangpf on 2017/2/10.
 */
@ContentView(R.layout.demo_sqlite)
public class SQLiteActivity extends Activity {
    private SimpleAdapter adapter;
    private ArrayList<HashMap<String, Object>> dataList;
    @ViewInject(R.id.txt_title)
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        title.setText("SQLite操作");
        ListView listView = (ListView) findViewById(R.id.ll_data);
        dataList = new ArrayList<HashMap<String, Object>>();
        adapter = new SimpleAdapter(this, dataList, R.layout.demo_sqlite_item,
                new String[]{"id","name","value","type"}, new int[]{R.id.txt_item_id,R.id.txt_item_name,R.id.txt_item_value,R.id.txt_item_type});
        listView.setAdapter(adapter);
    }

    /**
     * 数据库插入操作
     * @param view
     */
    public void saveData(View view) {
        DbManager db = x.getDb(((MyApplication)getApplicationContext()).getDaoConfig());
        SystemDictEntity entity = new SystemDictEntity();
        entity.setName("个人充电站");
        entity.setValue("01");
        entity.setType("type");
        try {
            db.saveBindingId(entity);
        }catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据库查询操作
     * @param view
     */
    public void findData(View view) {
        dataList.clear();

        DbManager db = x.getDb(((MyApplication)getApplicationContext()).getDaoConfig());
        try {
            List<SystemDictEntity> list = db.selector(SystemDictEntity.class).findAll();
            for(SystemDictEntity entity : list) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("id", entity.getId());
                map.put("name", entity.getName());
                map.put("value", entity.getValue());
                map.put("type", entity.getType());
                dataList.add(map);
            }
            adapter.notifyDataSetChanged();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
