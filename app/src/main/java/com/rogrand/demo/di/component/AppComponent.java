package com.rogrand.demo.di.component;

import com.rogrand.demo.app.DemoApp;
import com.rogrand.demo.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    DemoApp getContext(); // 提供App的Context

}
