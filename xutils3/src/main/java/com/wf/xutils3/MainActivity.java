package com.wf.xutils3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wf.xutils3.service.UploadApkService;
import com.wf.xutils3.utils.BaseActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * 一、使用IOC框架注解的成员变量及方法必须全部为私有，不然无效
 * 二，所有用到IOC成员变量，使用的时候，必须在x.view().inject(this)后，如果写在前面，那么程序会崩溃。
 * Created by wangpf on 2017/3/13.
 */
//@ContentView(R.layout.activity_main)
@ContentView(value = R.layout.activity_main)
public class MainActivity extends BaseActivity {
    /*@ViewInject(value = R.id.mybut)
    private Button mybut;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(value={R.id.btn_load_pic, R.id.btn_db, R.id.btn_net, R.id.btn_appdown},type = View.OnClickListener.class)
    private void onButtonClick(View v){
        switch (v.getId()){
            case R.id.btn_appdown:
                Intent intent = new Intent(MainActivity.this, UploadApkService.class);
                intent.putExtra("url", "http://www.izaodao.com/app/izaodao_app.apk");
                startService(intent);
                break;
            case R.id.btn_load_pic:
                Intent intent3 = new Intent(MainActivity.this, LoadPicActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_db:
                Intent intent2 = new Intent(MainActivity.this, SQLiteActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_net:
                doNet();
                break;
        }
    }

    private void doNet() {
        RequestParams params = new RequestParams("http://blog.csdn.net/mobile/experts.html");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                final String s = result;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                    }
                });
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(Callback.CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
}
