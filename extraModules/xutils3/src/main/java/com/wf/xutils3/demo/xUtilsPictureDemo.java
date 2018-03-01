package com.wf.xutils3.demo;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.wf.xutils3.R;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

/**
 * xUtils3.x图片模块demo
 * Created by wangpf on 2017/5/5.
 */
@ContentView(R.layout.demo_picture)
public class xUtilsPictureDemo extends Activity {
    @ViewInject(R.id.image01)
    private ImageView image01;
    @ViewInject(R.id.image02)
    private ImageView image02;
    @ViewInject(R.id.image03)
    private ImageView image03;

    String[] urls={
            "http://img.android.com/a.jpg",
            "http://img.android.com/b.jpg",
            "http://img.android.com/c.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    private void setPic() {
        /**
         * 通过ImageOptions.Builder().set方法设置图片的属性
         */
        ImageOptions options = new ImageOptions.Builder().setFadeIn(true).build(); //淡入效果
        //ImageOptions.Builder()的一些其他属性：
        //.setCircular(true) //设置图片显示为圆形
        //.setSquare(true) //设置图片显示为正方形
        //setCrop(true).setSize(200,200) //设置大小
        //.setAnimation(animation) //设置动画
        //.setFailureDrawable(Drawable failureDrawable) //设置加载失败的动画
        //.setFailureDrawableId(int failureDrawable) //以资源id设置加载失败的动画
        //.setLoadingDrawable(Drawable loadingDrawable) //设置加载中的动画
        //.setLoadingDrawableId(int loadingDrawable) //以资源id设置加载中的动画
        //.setIgnoreGif(false) //忽略Gif图片
        //.setParamsBuilder(ParamsBuilder paramsBuilder) //在网络请求中添加一些参数
        //.setRaduis(int raduis) //设置拐角弧度
        //.setUseMemCache(true) //设置使用MemCache，默认true

        /**
         * 加载图片的4个bind方法
         */
        x.image().bind(image01, urls[0]);
        x.image().bind(image02, urls[1], options);
        x.image().bind(image03, urls[2], options, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
        x.image().bind(image03, urls[3], options, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });

        /**
         * loadDrawable()方法加载图片
         */
        Callback.Cancelable cancelable = x.image().loadDrawable(urls[0], options, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                image03.setImageDrawable(result);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
        //主动取消loadDrawable()方法
        //cancelable.cancel();

        /**
         * loadFile()方法
         * 应用场景：当我们通过bind()或者loadDrawable()方法加载了一张图片后，
         * 它会保存到本地文件中，那当我需要这张图片时，就可以通过loadFile()方法进行查找。
         * urls[0]：网络地址
         */
        x.image().loadFile(urls[0],options,new Callback.CacheCallback<File>(){
            @Override
            public boolean onCache(File result) {
                //在这里可以做图片另存为等操作
                Log.i("JAVA","file："+result.getPath()+result.getName());
                return true; //相信本地缓存返回true
            }
            @Override
            public void onSuccess(File result) {
                Log.i("JAVA","file");
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
}
