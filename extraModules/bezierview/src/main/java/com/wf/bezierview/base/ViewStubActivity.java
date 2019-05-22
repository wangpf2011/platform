package com.wf.bezierview.base;

import android.os.Bundle;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.wf.bezierview.BaseActivity;
import com.wf.bezierview.R;

/**
 * ViewStub使用demo
 * Created by wang on 2019/2/28.
 */

public class ViewStubActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bezier_viewstub);
        pageName = "CustomViewActivity";

        if ((((int) (Math.random() * 100)) & 0x01) == 0) {
            // to show text
            // all you have to do is inflate the ViewStub for textview
            ViewStub stub = (ViewStub) findViewById(R.id.viewstub_demo_text);
            stub.inflate();
            TextView text = (TextView) findViewById(R.id.viewstub_demo_textview);
            text.setText("Android界面优化之ViewStub");
        } else {
            // to show image
            // all you have to do is inflate the ViewStub for imageview
            ViewStub stub = (ViewStub) findViewById(R.id.viewstub_demo_image);
            stub.inflate();
            ImageView image = (ImageView) findViewById(R.id.viewstub_demo_imageview);
            image.setImageResource(R.mipmap.bezier_bleheadericon);
        }

    }
}
