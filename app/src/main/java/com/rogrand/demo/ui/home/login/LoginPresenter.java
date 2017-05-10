package com.rogrand.demo.ui.home.login;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.rogrand.demo.base.RxPresenter;
import com.rogrand.demo.ui.home.register.RegisterActivity;

import javax.inject.Inject;

class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    public LoginPresenter() {

    }

    @Override
    public void onRegisterClick() {
        Intent intent = new Intent(mView.getContext(), RegisterActivity.class);
        mView.getContext().startActivity(intent);
    }

    @Override
    public void login(final Context context, String userName, String passWord) {

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
    public void onSinaLoginClick() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.sina.com"));
//        intent.putExtra(SearchManager.QUERY, "Sina");
        mView.getContext().startActivity(intent);
    }

    @Override
    public void onWechatLoginClick() {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, "Wechat");
        mView.getContext().startActivity(intent);
    }

    @Override
    public void onQQLoginClick() {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, "QQ");
        mView.getContext().startActivity(intent);
    }

    @Override
    public void onBaiduLoginCLick() {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, "Baidu");
        mView.getContext().startActivity(intent);
    }

}
