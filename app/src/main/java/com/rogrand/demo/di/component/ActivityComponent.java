package com.rogrand.demo.di.component;

import android.app.Activity;

import com.rogrand.demo.di.module.ActivityModule;
import com.rogrand.demo.di.scope.ActivityScope;
import com.rogrand.demo.ui.MainActivity;
import com.rogrand.demo.ui.home.login.LoginActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mMainActivity);

    void inject(LoginActivity mLoginActivity);
}