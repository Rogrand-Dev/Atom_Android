package com.rogrand.demo.ui.home.register;

import com.rogrand.demo.base.RxPresenter;

/**
 * 创建: 陈剑虹 17-5-10.
 */

public class RegisterPresenter extends RxPresenter<RegisterContract.View> implements RegisterContract.Presenter {


    @Override
    public void sendVerifyCode(String phoneNumber) {
        mView.showInputVerifyCode();
    }

    @Override
    public void confirmVerifyCode(String verifyCode) {
        mView.showInputPassword();
    }

    @Override
    public void setPassword(String password) {
        mView.showInputPhoneNumber();
    }
}
