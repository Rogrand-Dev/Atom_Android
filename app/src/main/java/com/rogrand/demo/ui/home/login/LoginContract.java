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
    }

    interface Presenter extends BasePresenter<View> {

        void onRegisterClick(Context context);

        void login(Context context, String userName, String passWord);

        void onPageChange(int pos);

        void onDrawerSwitchClick(boolean isDrawerOpen);

        void onSinaLoginClick(Context context);

        void onWechatLoginClick(Context context);

        void onQQLoginClick(Context context);

        void onBaiduLoginCLick(Context context);
    }
}
