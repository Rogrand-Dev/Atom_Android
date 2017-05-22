package com.rogrand.demo.di.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.rogrand.atom.utils.DataUtils;
import com.rogrand.atom.utils.LogUtils;
import com.rogrand.atom.utils.NetUtils;
import com.rogrand.atom.utils.SPUtils;
import com.rogrand.demo.app.Constants;
import com.rogrand.demo.app.DemoApp;
import com.rogrand.demo.http.UrlConstants;
import com.rogrand.demo.http.api.ApiService;
import com.rogrand.demo.http.cookie.CookieManger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {

    private static final String TAG = HttpModule.class.getSimpleName();
    private static final int TIME_OUT = 15; // 设置网络请求超时时间

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client);
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {

        if (DemoApp.getAppContext().isDebug()) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    LogUtils.i(TAG, message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor); // 设置 Log 拦截器
        }

        File cacheFile = new File(DataUtils.getCacheFile(DemoApp.getAppContext()), "RograndCache");
        int cacheSize = 100 * 1024 * 1024; // 100 MB
        Cache cache = new Cache(cacheFile, cacheSize);

        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                CacheControl.Builder cacheBuilder = new CacheControl.Builder();
                cacheBuilder.maxAge(0, TimeUnit.SECONDS);
                cacheBuilder.maxStale(365, TimeUnit.DAYS);
                CacheControl cacheControl = cacheBuilder.build();
                Request request = chain.request();
                if (!NetUtils.isNetworkAvailable(DemoApp.getAppContext())) {
                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetUtils.isNetworkAvailable(DemoApp.getAppContext())) {
                    int maxAge = 0; // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else { // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };

        // 设置 header（设置 Token、API 版本号）
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder()
                        .addHeader("Authorization", "Bearer " + SPUtils.get(DemoApp.getAppContext(), Constants.ACCESS_TOKEN, ""))
                        .addHeader("api_version", UrlConstants.API_VERSION)
                        .build();
                return chain.proceed(request);
            }
        };

        builder.addInterceptor(headerInterceptor); // 设置统一的请求头部参数
        builder.addNetworkInterceptor(cacheInterceptor); // 设置缓存
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS); // 设置连接超时
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS); // 设置读取数据超时
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS); // 设置写入数据超时
        builder.addNetworkInterceptor(new StethoInterceptor()); // 设置 Stetho
        builder.cookieJar(new CookieManger(DemoApp.getAppContext())); // 设置 Cookie
        builder.retryOnConnectionFailure(true); // 错误重连

        return builder.build();
    }

    @Singleton
    @Provides
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return builder
                .baseUrl(UrlConstants.API_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
