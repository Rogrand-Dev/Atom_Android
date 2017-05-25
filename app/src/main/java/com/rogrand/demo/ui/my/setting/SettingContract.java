package com.rogrand.demo.ui.my.setting;

import com.rogrand.demo.base.BasePresenter;
import com.rogrand.demo.base.BaseView;

interface SettingContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

        void logout();

    }
}
