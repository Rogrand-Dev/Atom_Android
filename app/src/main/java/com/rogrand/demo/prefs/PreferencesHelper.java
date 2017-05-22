package com.rogrand.demo.prefs;

public interface PreferencesHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getRefreshToken();

    void setRefreshToken(String refreshToken);

}
