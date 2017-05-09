package com.rogrand.demo.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import com.rogrand.atom.utils.ActivityManager;
import com.rogrand.demo.app.DemoApp;
import com.rogrand.demo.di.component.ActivityComponent;
import com.rogrand.demo.di.module.ActivityModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Activity 基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends SupportActivity implements BaseView {

    @Inject
    protected T mPresenter;
    protected Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        ButterKnife.bind(this);
        mContext = this;

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 禁止横屏
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
        ActivityManager.getInstance().addActivity(this);
        initEventAndData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /*protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(DemoApp.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        ActivityManager.getInstance().finishActivity(this);
    }

    protected abstract void initInject();

    protected abstract int getLayout();

    protected abstract void initEventAndData();

}
