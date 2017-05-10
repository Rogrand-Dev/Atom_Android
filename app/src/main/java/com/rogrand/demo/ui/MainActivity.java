package com.rogrand.demo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rogrand.atom.utils.ActivityManager;
import com.rogrand.atom.utils.LogUtils;
import com.rogrand.demo.R;
import com.rogrand.demo.base.BaseActivity;

import es.dmoral.toasty.Toasty;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation.helper.FragmentLifecycleCallbacks;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    private long mExitTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
        }

        // 可以监听该Activity下的所有Fragment的18个 生命周期方法
        registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks() {

            @Override
            public void onFragmentSupportVisible(SupportFragment fragment) {
                LogUtils.i("MainActivity", "onFragmentSupportVisible--->" + fragment.getClass().getSimpleName());
            }

            @Override
            public void onFragmentCreated(SupportFragment fragment, Bundle savedInstanceState) {
                super.onFragmentCreated(fragment, savedInstanceState);
            }
        });
    }

    @Override
    protected void initInject() {
//        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
    }

    @Override
    public void showError(String msg) {
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void onBackPressedSupport() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            Toasty.normal(MainActivity.this, "再按一次退出程序").show();
            mExitTime = System.currentTimeMillis();
        } else {
            ActivityManager.getInstance().exitApp();
        }
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
    }

}
