package com.rogrand.demo.ui.my.setting;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.rogrand.demo.R;
import com.rogrand.demo.base.BaseActivity;
import com.rogrand.demo.ui.my.setting.about.AboutActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_about)
    TextView mTvAbout;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "设置");
    }

    @OnClick(R.id.tv_about)
    void toAboutActivity() {
        Intent intent = new Intent(mContext, AboutActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

