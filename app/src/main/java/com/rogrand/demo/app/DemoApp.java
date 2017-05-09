package com.rogrand.demo.app;

import android.app.Application;

import com.rogrand.demo.di.component.AppComponent;
import com.rogrand.demo.di.module.AppModule;
import com.rogrand.demo.http.HttpManager;
import com.rogrand.demo.http.UrlConstants;

public class DemoApp extends Application {

    private static DemoApp appContext = null;
    private static AppComponent mAppComponent = null;
    public boolean isDebug = false; // 是否打开 debug 模式

    public static synchronized DemoApp getAppContext() {
        return appContext;
    }

    /*public static AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(appContext))
                    .build();
        }
        return mAppComponent;
    }*/

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;

        UrlConstants.init(this); // 初始化接口地址
        HttpManager.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppComponent != null)
            mAppComponent = null;
    }

    public boolean isDebug() {
        return isDebug;
    }
}
