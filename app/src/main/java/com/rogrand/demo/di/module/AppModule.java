package com.rogrand.demo.di.module;

import com.rogrand.demo.app.DemoApp;
import com.rogrand.demo.component.DataManager;
import com.rogrand.demo.http.HttpHelper;
import com.rogrand.demo.http.RetrofitHelper;
import com.rogrand.demo.prefs.ImplPreferencesHelper;
import com.rogrand.demo.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final DemoApp application;

    public AppModule(DemoApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    DemoApp provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper, preferencesHelper);
    }
}
