package com.wf.xutils3.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wf.xutils3.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * xUtils3.xActivity注解使用demo
 * Created by wangpf on 2017/5/5.
 */
@ContentView(R.layout.activity_main)
public class xUtilsActivityDemo extends AppCompatActivity {
    @ViewInject(R.id.btn_load_pic)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    /**
     * 用注解的方式为按钮添加点击事件，方法声明必须为private
     * type默认View.OnClickListener.class，故此处可以简化不写，@Event(R.id.bt_main)
     */
    @Event(type = View.OnClickListener.class,value = R.id.btn_load_pic)
    private void testInjectOnClick(View v){
        Toast.makeText(xUtilsActivityDemo.this,"加载图片", Toast.LENGTH_SHORT).show();
    }

    /**
     * 长按事件
     */
    @Event(type = View.OnLongClickListener.class,value = R.id.btn_db)
    private boolean testOnLongClickListener(View v){
        Toast.makeText(xUtilsActivityDemo.this,"操作数据库", Toast.LENGTH_SHORT).show();
        return true;
    }
}
