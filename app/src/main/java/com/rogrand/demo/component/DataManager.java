package com.rogrand.demo.component;

import com.rogrand.demo.bean.LoginBean;
import com.rogrand.demo.bean.MovieListBean;
import com.rogrand.demo.http.HttpHelper;
import com.rogrand.demo.http.api.ApiResponse;
import com.rogrand.demo.prefs.PreferencesHelper;

import io.reactivex.Flowable;

public class DataManager implements HttpHelper, PreferencesHelper {

    private HttpHelper mHttpHelper;
    private PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
    }

    @Override
    public String getRefreshToken() {
        return mPreferencesHelper.getRefreshToken();
    }

    @Override
    public void setRefreshToken(String refreshToken) {
        mPreferencesHelper.setRefreshToken(refreshToken);
    }

    @Override
    public Flowable<ApiResponse<LoginBean>> login(String userName, String verificationCode) {
        return mHttpHelper.login(userName, verificationCode);
    }

    @Override
    public Flowable<ApiResponse<MovieListBean>> getMovieList() {
        return mHttpHelper.getMovieList();
    }

}
