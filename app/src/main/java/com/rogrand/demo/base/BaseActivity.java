package com.rogrand.demo.base;

import com.rogrand.demo.app.DemoApp;
import com.rogrand.demo.di.component.ActivityComponent;
import com.rogrand.demo.di.component.DaggerActivityComponent;
import com.rogrand.demo.di.module.ActivityModule;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;

/**
 * Activity 基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {

    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(DemoApp.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showErrorMsg(String msg) {
        Toasty.normal(mContext, msg).show();
    }

    protected abstract void initInject();

}
