package com.lnint.pattern.mvc;

import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lnint.common.AppHelper;
import com.lnint.pattern.entity.RespInfo;
import com.lnint.pattern.entity.User;
import com.lnint.utils.HttpCookieStore;

import org.json.JSONObject;

/**
 * MVC的Model层
 * Created by wangpf on 2017/2/28.
 */

public class LoginNet {
    /**
     * 保存充电桩信息
     *
     * 同步请求
     * @param user
     * @return RespInfo
     */
    public RespInfo saveStakeInfo(User user) {
        RespInfo resp = new RespInfo();

        RequestParams params = new RequestParams();
        params.addBodyParameter("username", user.getUsername());
        params.addBodyParameter("password", user.getPassword());

        HttpUtils http = new HttpUtils();
        http.configCookieStore(HttpCookieStore.cookieStore);
        http.configCurrentHttpCacheExpiry(1000 * 10); //设置超时时间
        try {
            ResponseStream responseStream = http.sendSync(HttpRequest.HttpMethod.POST ,
                    AppHelper.HTTPRUL + "a/app/stake/bind/save",
                    params);
            //int statusCode = responseStream.getStatusCode();
            //Header[] headers = responseStream.getBaseResponse().getAllHeaders();
            String msg = responseStream.readString();
            JSONObject jsonObject = new JSONObject(msg);
            String success = jsonObject.getString("success");
            if("true".equals(success)) {//登录OK
                resp.setSuccess("true");
                resp.setData(jsonObject.getString("stakeno"));
            }else {
                resp.setSuccess("false");
                resp.setMessage(jsonObject.getString("message"));
            }
        } catch (Exception e) {
            resp.setSuccess("false");
            resp.setMessage("JSONObject resolve exception");
            Log.e("bluetooth", "JSONObject resolve exception");
            e.printStackTrace();
        }

        return resp;
    }
}
