package com.rogrand.demo.di.component;

import android.app.Activity;

import com.rogrand.demo.di.module.FragmentModule;
import com.rogrand.demo.di.scope.FragmentScope;
import com.rogrand.demo.ui.home.login.account.AccountLoginFragment;
import com.rogrand.demo.ui.home.login.mobile.MobileLoginFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(AccountLoginFragment accountLoginFragment);

    void inject(MobileLoginFragment mobileLoginFragment);
}
