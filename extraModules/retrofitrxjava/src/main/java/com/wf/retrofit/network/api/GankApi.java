// (c)2016 Flipboard Inc, All Rights Reserved.

package com.wf.retrofit.network.api;

import com.wf.retrofit.model.GankBeautyResult;
import com.wf.retrofit.model.Item;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GankApi {
    @GET("data/福利/{number}/{page}")
    Observable<GankBeautyResult> getBeauties(@Path("number") int number, @Path("page") int page);

    @GET("search")
    Observable<List<Item>> search(@Query("q") String query);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("app/v2/user/wxLogin")
    Observable<String> search1(@Body RequestBody body);

    //动态设置Header值
    @GET("user")
    Observable<String> getUser(@Header("Authorization") String authorization);

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("users/{username}")
    Observable<String> getUser1(@Path("username") String username);
}
