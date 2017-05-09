package com.rogrand.demo.ui;

import com.rogrand.demo.base.BasePresenter;
import com.rogrand.demo.base.BaseView;

interface MainContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
