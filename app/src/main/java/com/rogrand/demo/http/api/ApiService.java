package com.rogrand.demo.http.api;

import com.rogrand.demo.bean.LoginBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("oauth/api/account/login")
    Flowable<ApiResponse<LoginBean>> login(@FieldMap Map<String, String> params);


}
