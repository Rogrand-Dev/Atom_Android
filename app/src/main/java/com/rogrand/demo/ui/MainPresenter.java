package com.rogrand.demo.ui;

import com.rogrand.demo.base.RxPresenter;

import javax.inject.Inject;

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    @Inject
    public MainPresenter() {

    }
}
