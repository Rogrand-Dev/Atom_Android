package com.rogrand.demo.di.module;

import com.rogrand.demo.app.DemoApp;

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
}
