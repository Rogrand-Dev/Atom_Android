package com.rogrand.demo.ui.home.login;

import android.content.Context;

import com.rogrand.demo.base.BasePresenter;
import com.rogrand.demo.base.BaseView;

interface LoginContract {

    interface View extends BaseView {

        void loginSuccess(int authState);

        void showOtherLogins();

        void hideOtherLogins();

        void openOtherLoginsDrawer();

        void closeOtherLoginsDrawer();

        Context getContext();
    }

    interface Presenter extends BasePresenter<View> {

        void onRegisterClick();

        void login(Context context, String userName, String passWord);

        void onPageChange(int pos);

        void onDrawerSwitchClick(boolean isDrawerOpen);

        void onSinaLoginClick();

        void onWechatLoginClick();

        void onQQLoginClick();

        void onBaiduLoginCLick();
    }
}
