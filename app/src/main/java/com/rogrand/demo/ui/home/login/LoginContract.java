package com.rogrand.demo.ui.home.login;

import android.content.Context;

import com.rogrand.demo.base.BasePresenter;
import com.rogrand.demo.base.BaseView;

interface LoginContract {

    interface View extends BaseView {

        void loginSuccess(int authState);

    }

    interface Presenter extends BasePresenter<View> {

        void login(Context context, String userName, String passWord);

    }
}
