package com.rogrand.demo.http;

import com.rogrand.demo.bean.LoginBean;
import com.rogrand.demo.http.api.ApiResponse;

import io.reactivex.Flowable;

public interface HttpHelper {

    /**
     * @param userName         用户名
     * @param verificationCode 验证码
     */
    Flowable<ApiResponse<LoginBean>> login(String userName, String verificationCode);

}
