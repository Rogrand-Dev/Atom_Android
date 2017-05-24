package com.rogrand.demo.ui.home.login;

import android.animation.ObjectAnimator;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import com.rogrand.demo.R;
import com.rogrand.demo.base.BaseActivity;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab_cat_eye_login)
    TabLayout mTabLayout;
    @BindView(R.id.vp_cat_eye_login)
    ViewPager mViewPager;
    @BindView(R.id.vg_cat_eye_login_other_logins)
    ViewGroup mVgOtherLogins;
    @BindView(R.id.iv_cat_eye_login_switch)
    FrameLayout mIvSwitch;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "登录");
        mToolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_register:
                    mPresenter.onRegisterClick(mContext);
                    break;
            }
            return true;
        });
        mViewPager.setAdapter(new LoginAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPresenter.onPageChange(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mIvSwitch.setOnClickListener(v -> mPresenter.onDrawerSwitchClick(isDrawerOpen));

    }

    @Override
    public void showOtherLogins() {
        mVgOtherLogins.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideOtherLogins() {
        mVgOtherLogins.setVisibility(View.GONE);
    }

    float transY;
    private boolean isDrawerOpen = true;

    @Override
    public void openOtherLoginsDrawer() {
        isDrawerOpen = true;
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setPropertyName("translationY");
        objectAnimator.setDuration(200);
        objectAnimator.setFloatValues(transY, 0);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setTarget(mVgOtherLogins);
        objectAnimator.start();
    }

    @Override
    public void closeOtherLoginsDrawer() {
        isDrawerOpen = false;
        if (transY == 0)
            transY = mVgOtherLogins.getHeight();
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setPropertyName("translationY");
        objectAnimator.setDuration(200);
        objectAnimator.setFloatValues(0, transY);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setTarget(mVgOtherLogins);
        objectAnimator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

}

