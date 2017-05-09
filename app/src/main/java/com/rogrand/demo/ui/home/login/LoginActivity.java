package com.rogrand.demo.ui.home.login;

import com.rogrand.demo.R;
import com.rogrand.demo.base.BaseActivity;
import com.rogrand.demo.bean.LoginBean;
import com.rogrand.demo.http.HttpManager;
import com.rogrand.demo.http.callback.OnResultCallBack;
import com.rogrand.demo.http.subscriber.HttpSubscriber;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    private HttpSubscriber mHttpObserver;

    @Override
    protected void initInject() {
//        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {

        mHttpObserver = new HttpSubscriber(new OnResultCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {

            }

            @Override
            public void onError(int code, String errorMsg) {

            }
        });

        HttpManager.getInstance().loginNoCache(mHttpObserver, "", "");
    }

    @Override
    public void loginSuccess(int authState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHttpObserver.unSubscribe();
    }

    @Override
    public void showError(String msg) {

    }

}

