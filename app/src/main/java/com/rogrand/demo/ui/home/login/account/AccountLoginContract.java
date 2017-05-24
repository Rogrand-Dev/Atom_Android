package com.rogrand.demo.ui.home.login.account;

import android.content.Context;

import com.rogrand.demo.base.BasePresenter;
import com.rogrand.demo.base.BaseView;

interface AccountLoginContract {

    interface View extends BaseView {

        void loginAccountSuccess();
    }

    interface Presenter extends BasePresenter<View> {

        void loginAccount(Context context, String userName, String passWord);

    }
}
