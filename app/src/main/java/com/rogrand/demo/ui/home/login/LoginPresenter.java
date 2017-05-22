package com.rogrand.demo.ui.home.login;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.rogrand.demo.base.RxPresenter;
import com.rogrand.demo.bean.LoginBean;
import com.rogrand.demo.component.DataManager;
import com.rogrand.demo.http.RxSubUtils;
import com.rogrand.demo.http.api.ApiResponse;
import com.rogrand.demo.ui.home.register.RegisterActivity;
import com.rogrand.demo.utils.RxUtils;

import javax.inject.Inject;

class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void onRegisterClick(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void login(final Context context, String userName, String passWord) {
        addSubscribe(mDataManager.login(userName, passWord)
                .compose(RxUtils.<ApiResponse<LoginBean>>rxSchedulerHelper())
                .compose(RxUtils.<LoginBean>handleResult())
                .subscribeWith(new RxSubUtils<LoginBean>(context, "正在登录...") {

                    @Override
                    public void _onNext(LoginBean mLoginBean) {

                    }

                    @Override
                    public void _onError(int errorCode, String message) {
                        mView.showErrorMsg(message);
                    }
                }));
    }

    @Override
    public void onPageChange(int pos) {
        if (pos == 0) {
            mView.showOtherLogins();
        } else {
            mView.hideOtherLogins();
        }
    }

    @Override
    public void onDrawerSwitchClick(boolean isDrawerOpen) {
        if (isDrawerOpen) {
            mView.closeOtherLoginsDrawer();
        } else {
            mView.openOtherLoginsDrawer();
        }
    }

    @Override
    public void onSinaLoginClick(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.sina.com"));
//        intent.putExtra(SearchManager.QUERY, "Sina");
        context.startActivity(intent);
    }

    @Override
    public void onWechatLoginClick(Context context) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, "Wechat");
        context.startActivity(intent);
    }

    @Override
    public void onQQLoginClick(Context context) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, "QQ");
        context.startActivity(intent);
    }

    @Override
    public void onBaiduLoginCLick(Context context) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, "Baidu");
        context.startActivity(intent);
    }

}
