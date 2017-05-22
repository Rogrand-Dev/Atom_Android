package com.rogrand.demo.di.component;

import com.rogrand.demo.app.DemoApp;
import com.rogrand.demo.component.DataManager;
import com.rogrand.demo.di.module.AppModule;
import com.rogrand.demo.di.module.HttpModule;
import com.rogrand.demo.http.RetrofitHelper;
import com.rogrand.demo.prefs.ImplPreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    DemoApp getContext(); // 提供App的Context

    DataManager getDataManager(); // 数据中心

    RetrofitHelper retrofitHelper(); // 提供 Http 的帮助类

    ImplPreferencesHelper preferencesHelper(); // 提供 sp 帮助类

}
