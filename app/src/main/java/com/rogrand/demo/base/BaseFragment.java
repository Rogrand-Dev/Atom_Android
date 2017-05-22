package com.rogrand.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.rogrand.demo.app.DemoApp;
import com.rogrand.demo.di.component.DaggerFragmentComponent;
import com.rogrand.demo.di.component.FragmentComponent;
import com.rogrand.demo.di.module.FragmentModule;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;

/**
 * Fragment 基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(DemoApp.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        Toasty.normal(mContext, msg).show();
    }

    protected abstract void initInject();
}