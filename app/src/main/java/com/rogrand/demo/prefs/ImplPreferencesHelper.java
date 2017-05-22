package com.rogrand.demo.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.rogrand.demo.app.Constants;
import com.rogrand.demo.app.DemoApp;

import javax.inject.Inject;

public class ImplPreferencesHelper implements PreferencesHelper {

    private static final String SHAREDPREFERENCES_NAME = "sp_rogrand";

    private final SharedPreferences mSPrefs;

    @Inject
    public ImplPreferencesHelper() {
        mSPrefs = DemoApp.getAppContext().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public String getAccessToken() {
        return mSPrefs.getString(Constants.ACCESS_TOKEN, "");
    }

    @Override
    public void setAccessToken(String accessToken) {
        mSPrefs.edit().putString(Constants.ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public String getRefreshToken() {
        return mSPrefs.getString(Constants.REFRESH_TOKEN, "");
    }

    @Override
    public void setRefreshToken(String refreshToken) {
        mSPrefs.edit().putString(Constants.REFRESH_TOKEN, refreshToken).apply();
    }
}
