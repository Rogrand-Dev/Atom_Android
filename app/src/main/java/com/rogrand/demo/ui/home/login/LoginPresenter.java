package com.rogrand.demo.ui.home.login;

import android.content.Context;

import com.rogrand.demo.base.RxPresenter;

import javax.inject.Inject;

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    public LoginPresenter() {

    }

    @Override
    public void login(final Context context, String userName, String passWord) {

    }

}
