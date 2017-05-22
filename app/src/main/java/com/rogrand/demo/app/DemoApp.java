package com.rogrand.demo.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.rogrand.demo.R;
import com.rogrand.demo.di.component.AppComponent;
import com.rogrand.demo.di.component.DaggerAppComponent;
import com.rogrand.demo.di.module.AppModule;
import com.rogrand.demo.di.module.HttpModule;
import com.rogrand.demo.http.UrlConstants;

public class DemoApp extends Application {

    private static DemoApp appContext = null;
    private static AppComponent mAppComponent = null;
    public boolean isDebug = false;

    public static synchronized DemoApp getAppContext() {
        return appContext;
    }

    public static AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(appContext))
                    .httpModule(new HttpModule())
                    .build();
        }
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = this;
        setDebug(getResources().getBoolean(R.bool.is_debug));
        UrlConstants.init(this); // 初始化接口地址
        initStetho();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppComponent != null)
            mAppComponent = null;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public boolean isDebug() {
        return isDebug;
    }

    /**
     * 初始化 Stetho
     */
    private void initStetho() {
        if (isDebug())
            Stetho.initializeWithDefaults(this);
    }
}
