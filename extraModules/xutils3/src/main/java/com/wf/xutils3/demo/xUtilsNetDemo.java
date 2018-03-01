package com.wf.xutils3.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * xUtils3.x网络请求demo
 * Created by wangpf on 2017/5/5.
 */
public class xUtilsNetDemo {
    private String url = "http://www.android.com";

    //GET请求
    private void get(View v){
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("username","abc");
        params.addQueryStringParameter("password","123");
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("Java", "onSuccess result:" + result);
            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
        //主动调用取消请求
        cancelable.cancel();
    }

    //POST请求
    private void post(View v){
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("username","abc");
        params.addParameter("password","123");
        params.addHeader("head","android"); //为当前请求添加一个头
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
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

    //其他网络请求方式
    private void other(View v){
        RequestParams params = new RequestParams(url);
        params.addParameter("username","abc");
        x.http().request(HttpMethod.PUT, params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
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

    //上传文件
    private void upload(View v){
        String path="/mnt/sdcard/Download/icon.jpg";
        RequestParams params = new RequestParams(url);
        params.setMultipart(true);
        params.addBodyParameter("file",new File(path));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
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

    //下载文件
    //这里以下载apk为例进行说明，apk下载完成后，自动调用系统的安装方法。
    private void download(View v){
        url = "http://127.0.0.1/server/ABC.apk";
        RequestParams params = new RequestParams(url);
        //自定义保存路径，Environment.getExternalStorageDirectory()：SD卡的根目录
        params.setSaveFilePath(Environment.getExternalStorageDirectory()+"/myapp/");
        //自动为文件命名
        params.setAutoRename(true);
        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                //apk下载完成后，调用系统的安装方法
                /*Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive");
                getActivity().startActivity(intent);*/
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
            //网络请求之前回调
            @Override
            public void onWaiting() {
            }
            //网络请求开始的时候回调
            @Override
            public void onStarted() {
            }
            //下载的时候不断回调的方法
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                //当前进度和文件总大小
                Log.i("JAVA","current："+ current +"，total："+total);
            }
        });
    }

    //使用缓存
    private void cache(View v) {
        RequestParams params = new RequestParams(url);
        params.setCacheMaxAge(1000*60); //为请求添加缓存时间
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("JAVA","onSuccess："+result);
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
            //result：缓存内容
            @Override
            public boolean onCache(String result) {
                //在setCacheMaxAge设置范围（上面设置的是60秒）内，如果再次调用GET请求，
                //返回true：缓存内容被返回，相信本地缓存，返回false：缓存内容被返回，不相信本地缓存，仍然会请求网络
                Log.i("JAVA","cache："+result);
                return true;
            }
        });
    }
}
