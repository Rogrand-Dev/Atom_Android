package com.rogrand.demo.ui.home.login;

import android.content.Context;
import android.content.Intent;

import com.rogrand.demo.base.RxPresenter;
import com.rogrand.demo.ui.home.register.RegisterActivity;

import javax.inject.Inject;

class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    public LoginPresenter() {

    }

    @Override
    public void onPageChange(int pos) {
        if (pos == 0) {
            mView.showOtherLogins();
        } else {
            mView.hideOtherLogins();
        }
    }

    @Override
    public void onDrawerSwitchClick(boolean isDrawerOpen) {
        if (isDrawerOpen) {
            mView.closeOtherLoginsDrawer();
        } else {
            mView.openOtherLoginsDrawer();
        }
    }

    @Override
    public void onRegisterClick(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

}
