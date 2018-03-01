package com.wf.xutils3.demo;

import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wf.xutils3.utils.UserEntity;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * xUtils3+网络请求demo
 *
 * Created by wangpf on 2017/4/19.
 */

public class xUtilsNetDemo1 {
    String url = "http://localhost:8080/evms/vehicle/list";

    //post网络请求
    public void postDemo() {
        RequestParams params = new RequestParams(url);
        params.addParameter("name", "wangpf");
        //addBodyParameter将参数添加到网络请求的body中
        params.addBodyParameter("tel", "13000000000");
        //根据当前网络请求的方式不同将参数添加到不同的地方
        //post将参数添加到body中，相当于addBodyParameter方法
        //get将参数添加到uri的后面，相当于addQueryStringParameter方法
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (ex instanceof HttpException) { // 网络错误
                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();
                    // ...
                } else { // 其他错误
                    // ...
                }
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "onCancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {
                Toast.makeText(x.app(), "onFinished", Toast.LENGTH_LONG).show();
            }
        });
    }

    //post方法传json参数
    public void postJsonDemo() {
        UserEntity user = new UserEntity("张三", "13000000000");
        String json = JSON.toJSONString(user);
        RequestParams params = new RequestParams(url);
        params.setAsJsonContent(true);
        params.setBodyContent(json);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "onCancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {
                Toast.makeText(x.app(), "onFinished", Toast.LENGTH_LONG).show();
            }
        });

        /*//后台部分代码
        @RequestMapping("/add")
        @ResponseBody
        public List add(@RequestBody User user){
            userService.add(user);
            return userService.findAll();
        };*/
    }

    //get网络请求
    public void getDemo() {
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("name", "wangpf");
        params.addQueryStringParameter("tel", "13000000000");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
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

    //文件上传
    public void fileDemo() {
        /**
         * 自定义实体参数类请参考:
         * 请求注解 {@link org.xutils.http.annotation.HttpRequest}
         * 请求注解处理模板接口 {@link org.xutils.http.app.ParamsBuilder}
         *
         * 需要自定义类型作为callback的泛型时, 参考:
         * 响应注解 {@link org.xutils.http.annotation.HttpResponse}
         * 响应注解处理模板接口 {@link org.xutils.http.app.ResponseParser}
         *
         * 示例: 查看 org.xutils.sample.http 包里的代码
         */
        RequestParams params = new RequestParams(url);
        // 有上传文件时使用multipart表单, 否则上传原始文件流.
        // params.setMultipart(true);
        // 上传文件方式 1
        // params.uploadFile = new File("/sdcard/test.txt");
        // 上传文件方式 2
        params.addBodyParameter("uploadFile", new File("/sdcard/test.txt"));
        Callback.Cancelable cancelable
                = x.http().post(params,
                /**
                 * 1. callback的泛型:
                 * callback参数默认支持的泛型类型参见{@link org.xutils.http.loader.LoaderFactory},
                 * 例如: 指定泛型为File则可实现文件下载, 使用params.setSaveFilePath(path)指定文件保存的全路径.
                 * 默认支持断点续传(采用了文件锁和尾端校验续传文件的一致性).
                 * 其他常用类型可以自己在LoaderFactory中注册,
                 * 也可以使用{@link org.xutils.http.annotation.HttpResponse}
                 * 将注解HttpResponse加到自定义返回值类型上, 实现自定义ResponseParser接口来统一转换.
                 * 如果返回值是json形式, 那么利用第三方的json工具将十分容易定义自己的ResponseParser.
                 * 如示例代码{@link org.xutils.sample.http.BaiduResponse}, 可直接使用BaiduResponse作为
                 * callback的泛型.
                 *
                 * 2. callback的组合:
                 * 可以用基类或接口组合个种类的Callback, 见{@link Callback}.
                 * 例如:
                 * a. 组合使用CacheCallback将使请求检测缓存或将结果存入缓存(仅GET请求生效).
                 * b. 组合使用PrepareCallback的prepare方法将为callback提供一次后台执行耗时任务的机会,
                 * 然后将结果给onCache或onSuccess.
                 * c. 组合使用ProgressCallback将提供进度回调.
                 * ...(可参考{@link org.xutils.image.ImageLoader}
                 * 或 示例代码中的 {@link org.xutils.sample.download.DownloadCallback})
                 *
                 * 3. 请求过程拦截或记录日志: 参考 {@link org.xutils.http.app.RequestTracker}
                 *
                 * 4. 请求Header获取: 参考 {@link org.xutils.http.app.InterceptRequestListener}
                 *
                 * 5. 其他(线程池, 超时, 重定向, 重试, 代理等): 参考 {@link RequestParams
                 *
                 **/
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        //Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        if (ex instanceof HttpException) { // 网络错误
                            HttpException httpEx = (HttpException) ex;
                            int responseCode = httpEx.getCode();
                            String responseMsg = httpEx.getMessage();
                            String errorResult = httpEx.getResult();
                            // ...
                        } else { // 其他错误
                            // ...
                        }
                        Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFinished() {

                    }
                });

        // cancelable.cancel(); // 取消
        // 如果需要记录请求的日志, 可使用RequestTracker接口(优先级依次降低, 找到一个实现后会忽略后面的):
        // 1. 自定义Callback同时实现RequestTracker接口;
        // 2. 自定义ResponseParser同时实现RequestTracker接口;
        // 3. 在LoaderFactory注册.
    }

    //同步请求
    public void syncDemo() {
        try {
            RequestParams params = new RequestParams(url);
            params.addBodyParameter("user", "wangpf");
            params.addBodyParameter("tel", "13000000000");
            //这里就是使用同步请求的地方了，只有一句代码，可以直接返回一个对象。
            UserEntity userInfo = x.http().postSync(params, UserEntity.class);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
