package com.rogrand.demo.http;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.rogrand.atom.utils.LogUtils;
import com.rogrand.atom.utils.SignUtils;
import com.rogrand.demo.app.Constants;
import com.rogrand.demo.app.DemoApp;
import com.rogrand.demo.bean.LoginBean;
import com.rogrand.demo.http.api.ApiResponse;
import com.rogrand.demo.http.api.ApiService;
import com.rogrand.demo.http.cache.CacheProvider;
import com.rogrand.demo.http.exception.ApiException;
import com.rogrand.demo.http.parser.GsonTSpeaker;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.internal.RxCache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    private static final String TAG = HttpManager.class.getSimpleName();
    private ApiService mApiService;
    private final CacheProvider cacheProvider;
    private static Context mContext;
    private volatile static HttpManager instance;

    private HttpManager() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.i(TAG, message);
            }
        });
        if (DemoApp.getAppContext().isDebug()) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS) // 设置连接超时
                .retryOnConnectionFailure(true) // 错误重连
                .addInterceptor(loggingInterceptor); // 设置 Log 拦截器

        OkHttpClient okHttpClient = builder.build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(UrlConstants.API_URL)
                .client(okHttpClient)
                .build();

        cacheProvider = new RxCache.Builder()
                .persistence(mContext.getFilesDir(), new GsonTSpeaker())
                .using(CacheProvider.class);

        mApiService = mRetrofit.create(ApiService.class);
    }

    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    public static void init(Context context) {
        mContext = context;
    }

    private <T> void toSubscribe(Observable<ApiResponse<T>> o, Observer<T> s) {
        o.subscribeOn(Schedulers.io())
                .map(new Function<ApiResponse<T>, T>() {
                    @Override
                    public T apply(@NonNull ApiResponse<T> response) throws Exception {
                        int code = response.getCode();
                        if (code != UrlConstants.SUCCESS_CODE) {
                            throw new ApiException(code, response.getMsg());
                        } else {
                            return response.getData();
                        }
                    }
                })
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    public void loginWithCache(Observer<LoginBean> subscriber, String userName, String verificationCode, boolean update) {

        HashMap<String, String> params = new HashMap<>();
        params.put("username", userName);
        params.put("password", "password");
        params.put("verification_code", verificationCode);
        params.put("client_id", UrlConstants.TOKEN_CLIENT_ID);
        params.put("client_secret", UrlConstants.TOKEN_CLIENT_SECRET);
        params.put("grant_type", "password");
        params.put("scope", "read");
        params.put("device_id", "");
        params.put("sign", SignUtils.getInstance().sign(params, Constants.MD5_SECRET));

        toSubscribe(cacheProvider.login(mApiService.login(params), new EvictProvider(update)), subscriber);
    }

    public void loginNoCache(Observer<LoginBean> subscriber, String userName, String verificationCode) {

        HashMap<String, String> params = new HashMap<>();
        params.put("username", userName);
        params.put("password", "password");
        params.put("verification_code", verificationCode);
        params.put("client_id", UrlConstants.TOKEN_CLIENT_ID);
        params.put("client_secret", UrlConstants.TOKEN_CLIENT_SECRET);
        params.put("grant_type", "password");
        params.put("scope", "read");
        params.put("device_id", "");
        params.put("sign", SignUtils.getInstance().sign(params, Constants.MD5_SECRET));

        toSubscribe(mApiService.login(params), subscriber);
    }

    /**
     * 登录
     *
     * @param userName         用户名
     * @param verificationCode 验证码
     */
    public Observable<ApiResponse<LoginBean>> login(String userName, String verificationCode) {

        HashMap<String, String> params = new HashMap<>();
        params.put("username", userName);
        params.put("password", "password");
        params.put("verification_code", verificationCode);
        params.put("client_id", UrlConstants.TOKEN_CLIENT_ID);
        params.put("client_secret", UrlConstants.TOKEN_CLIENT_SECRET);
        params.put("grant_type", "password");
        params.put("scope", "read");
        params.put("device_id", "");
        params.put("sign", SignUtils.getInstance().sign(params, Constants.MD5_SECRET));

        return mApiService.login(params);
    }

}
