package com.rogrand.demo.ui.my.userinfo;

import com.rogrand.demo.base.RxPresenter;

import javax.inject.Inject;

class UserInfoPresenter extends RxPresenter<UserInfoContract.View> implements UserInfoContract.Presenter {

    @Inject
    public UserInfoPresenter() {

    }
}
