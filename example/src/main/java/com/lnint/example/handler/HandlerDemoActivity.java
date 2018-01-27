package com.lnint.example.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lnint.corelib.CustomHandler;
import com.lnint.example.R;

/**
 * Demo--Handler
 * Created by wangpf on 2017/2/7.
 */
public class HandlerDemoActivity extends Activity {
    private static final int MSG_MSG = 0;
    private static final int MSG_ERROR = 1;
    //Handler对HandlerDemoActivity使用了弱引用
    private CustomHandler<HandlerDemoActivity> msgHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.demo_handler_message);
        initHandler();

        Button sure = (Button)findViewById(R.id.btn_sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //普通Message
                msgHandler.obtainMessage(MSG_MSG, "确定").sendToTarget();
            }
        });
        Button quit = (Button)findViewById(R.id.btn_quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Post方式，直接执行Runnable的run()方法
                msgHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        msgHandler.obtainMessage(MSG_ERROR, "取消").sendToTarget();
                    }
                });
            }
        });
    }

    /**
     * Handler处理消息
     */
    private void initHandler() {
        msgHandler = new CustomHandler<HandlerDemoActivity>(this) {
            @Override
            public void handleMessage(Message msg) {
                HandlerDemoActivity outer = this.getReference().get();
                if(outer != null) {
                    switch(msg.what) {
                        case MSG_MSG:
                            if(msg.obj != null) {
                                Toast.makeText(outer, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case MSG_ERROR:
                            if(msg.obj != null) {
                                Toast.makeText(outer, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                }
            }
        };
    }
}