package com.rogrand.demo.ui.home.login.mobile;

import android.content.Context;

import com.rogrand.demo.base.RxPresenter;
import com.rogrand.demo.bean.LoginBean;
import com.rogrand.demo.component.DataManager;
import com.rogrand.demo.http.RxSubUtils;
import com.rogrand.demo.http.api.ApiResponse;
import com.rogrand.demo.utils.RxUtils;

import javax.inject.Inject;

class MobileLoginPresenter extends RxPresenter<MobileLoginContract.View> implements MobileLoginContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public MobileLoginPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }


    @Override
    public void getVCode(final Context context, String tel) {

    }

    @Override
    public void loginVCode(final Context context, String tel, String vCode) {

    }
}


