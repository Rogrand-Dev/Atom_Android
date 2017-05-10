package com.rogrand.demo.ui.home.register;

import com.rogrand.demo.base.BasePresenter;
import com.rogrand.demo.base.BaseView;

/**
 * 创建: 陈剑虹 17-5-10.
 */

interface RegisterContract {

    interface View extends BaseView {
        void showInputPhoneNumber();

        void showInputVerifyCode();

        void showInputPassword();

    }

    interface Presenter extends BasePresenter<View> {
        void sendVerifyCode(String phoneNumber);

        void confirmVerifyCode(String verifyCode);

        void setPassword(String password);
    }
}
