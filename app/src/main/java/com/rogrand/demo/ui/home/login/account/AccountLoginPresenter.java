package com.rogrand.demo.ui.home.login.account;

import android.content.Context;

import com.rogrand.demo.base.RxPresenter;
import com.rogrand.demo.bean.LoginBean;
import com.rogrand.demo.component.DataManager;
import com.rogrand.demo.http.RxSubUtils;
import com.rogrand.demo.http.api.ApiResponse;
import com.rogrand.demo.utils.RxUtils;

import javax.inject.Inject;

class AccountLoginPresenter extends RxPresenter<AccountLoginContract.View> implements AccountLoginContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public AccountLoginPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void loginAccount(final Context context, String userName, String passWord) {
        addSubscribe(mDataManager.login(userName, passWord)
                .compose(RxUtils.<ApiResponse<LoginBean>>rxSchedulerHelper())
                .compose(RxUtils.<LoginBean>handleResult())
                .subscribeWith(new RxSubUtils<LoginBean>(context, "正在登录...") {

                    @Override
                    public void _onNext(LoginBean mLoginBean) {
                        mView.loginAccountSuccess();
                    }

                    @Override
                    public void _onError(int errorCode, String message) {
                        mView.showErrorMsg(message);
                    }
                }));
    }

}
