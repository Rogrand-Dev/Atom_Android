package com.rogrand.demo.di.component;

import android.app.Activity;

import com.rogrand.demo.di.module.ActivityModule;
import com.rogrand.demo.di.scope.ActivityScope;
import com.rogrand.demo.ui.MainActivity;
import com.rogrand.demo.ui.home.login.LoginActivity;
import com.rogrand.demo.ui.home.recycler.RecyclerViewActivity;
import com.rogrand.demo.ui.home.register.RegisterActivity;
import com.rogrand.demo.ui.home.webview.WebViewActivity;
import com.rogrand.demo.ui.my.setting.SettingActivity;
import com.rogrand.demo.ui.my.userinfo.UserInfoActivity;
import com.rogrand.demo.ui.my.userinfo.mycity.MyCityActivity;

import org.jetbrains.annotations.NotNull;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mMainActivity);

    void inject(LoginActivity mLoginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(SettingActivity settingActivity);

    void inject(UserInfoActivity userInfoActivity);

    void inject(@NotNull WebViewActivity webViewActivity);

    void inject(RecyclerViewActivity mRecyclerViewActivity);

    void inject(MyCityActivity mMyCityActivity);
}
