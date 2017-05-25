package com.rogrand.demo.ui.my.userinfo;

import android.support.v7.widget.Toolbar;

import com.rogrand.demo.R;
import com.rogrand.demo.base.BaseActivity;

import butterknife.BindView;

public class UserInfoActivity extends BaseActivity<UserInfoPresenter> implements UserInfoContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "个人信息");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

