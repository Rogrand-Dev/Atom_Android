package com.rogrand.demo.http.api;

import com.rogrand.demo.bean.LoginBean;
import com.rogrand.demo.bean.MovieListBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("oauth/api/account/login")
    Flowable<ApiResponse<LoginBean>> login(@FieldMap Map<String, String> params);

    @GET("Su117S75b6c84f625f40dab8fbbc4deb06b7f776a0b967f?uri=movie/list")
    Flowable<ApiResponse<MovieListBean>> getMovieList();

}
