package com.rogrand.demo.di.component;

import android.app.Activity;

import com.rogrand.demo.di.module.FragmentModule;
import com.rogrand.demo.di.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

//    void inject(HomeTabFragment homeTabFragment);
}
