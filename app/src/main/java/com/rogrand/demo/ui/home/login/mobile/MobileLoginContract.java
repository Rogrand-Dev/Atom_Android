package com.rogrand.demo.ui.home.login.mobile;

import android.content.Context;

import com.rogrand.demo.base.BasePresenter;
import com.rogrand.demo.base.BaseView;

interface MobileLoginContract {

    interface View extends BaseView {

        void VCodeloginSuccess();
    }

    interface Presenter extends BasePresenter<View> {

        void getVCode(Context context, String tel);

        void loginVCode(Context context, String tel, String vCode);

    }
}
