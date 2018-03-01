package com.wf.xutils3;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.wf.xutils3.utils.BaseActivity;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * xUtils加载图片功能
 * Created by wangpf on 2017/3/13.
 */
@ContentView(value = R.layout.demo_loadpic)
public class LoadPicActivity extends BaseActivity {
    @ViewInject(R.id.img_net)
    private ImageView image;
    @ViewInject(R.id.txt_title)
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        title.setText("加载图片");
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))//图片大小
                .setRadius(DensityUtil.dip2px(5))//ImageView圆角半径
                .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_launcher)//加载中默认显示图片
                .setFailureDrawableId(R.mipmap.ic_launcher)//加载失败后默认显示图片
                .build();
        x.image().bind(image, "http://pic.baike.soso.com/p/20090711/20090711101754-314944703.jpg",imageOptions);
    }
}
