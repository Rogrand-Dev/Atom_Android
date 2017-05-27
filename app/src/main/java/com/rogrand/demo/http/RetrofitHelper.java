package com.rogrand.demo.http;

import com.rogrand.atom.utils.SignUtils;
import com.rogrand.demo.app.Constants;
import com.rogrand.demo.bean.LoginBean;
import com.rogrand.demo.http.api.ApiResponse;
import com.rogrand.demo.http.api.ApiService;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class RetrofitHelper implements HttpHelper {

    private static ApiService mApiService;

    @Inject
    public RetrofitHelper(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Flowable<ApiResponse<LoginBean>> login(String userName, String verificationCode) {

        HashMap<String, String> params = new HashMap<>();

        params.put("city", "武汉");
        params.put("latitude", "30.562339");
        params.put("longitude", "114.511848");
        params.put("region", "洪山区");

        /*params.put("username", userName);
        params.put("password", "password");
        params.put("verification_code", verificationCode);
        params.put("client_id", UrlConstants.TOKEN_CLIENT_ID);
        params.put("client_secret", UrlConstants.TOKEN_CLIENT_SECRET);
        params.put("grant_type", "password");
        params.put("scope", "read");*/
        params.put("sign", SignUtils.getInstance().sign(params, Constants.ENCRYPT_SECRET,"MD5"));

        return mApiService.login(params);
    }
}
