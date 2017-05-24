package com.rogrand.demo.ui.home.login;

import android.content.Context;

import com.rogrand.demo.base.BasePresenter;
import com.rogrand.demo.base.BaseView;

interface LoginContract {

    interface View extends BaseView {

        void showOtherLogins();

        void hideOtherLogins();

        void openOtherLoginsDrawer();

        void closeOtherLoginsDrawer();
    }

    interface Presenter extends BasePresenter<View> {

        void onPageChange(int pos);

        void onDrawerSwitchClick(boolean isDrawerOpen);

        void onRegisterClick(Context context);

    }
}
