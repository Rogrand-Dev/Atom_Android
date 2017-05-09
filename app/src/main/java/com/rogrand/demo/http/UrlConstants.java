package com.rogrand.demo.http;

import android.content.Context;

import com.rogrand.demo.R;

public class UrlConstants {

    public static String API_URL = ""; // 服务器地址
    public static String TOKEN_CLIENT_ID = ""; // token client id
    public static String TOKEN_CLIENT_SECRET = ""; // token client secret
    public static String API_VERSION = ""; // 接口版本号
    public static int PAGESIZE = 0; // 每页多少条数据
    public static final int SUCCESS_CODE = 0; // 状态码：成功

    public static void init(Context context) {

        API_URL = context.getResources().getString(R.string.api_host) + context.getResources().getString(R.string.api_name_prefix);
        TOKEN_CLIENT_ID = context.getString(R.string.token_client_id);
        TOKEN_CLIENT_SECRET = context.getString(R.string.token_client_secret);
        PAGESIZE = context.getResources().getInteger(R.integer.url_pagesize);
        API_VERSION = context.getString(R.string.api_version);
    }
}
