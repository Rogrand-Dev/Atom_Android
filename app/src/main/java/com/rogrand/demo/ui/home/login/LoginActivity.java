package com.rogrand.demo.ui.home.login;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rogrand.demo.R;
import com.rogrand.demo.base.BaseActivity;
import com.rogrand.demo.bean.LoginBean;
import com.rogrand.demo.http.HttpManager;
import com.rogrand.demo.http.callback.OnResultCallBack;
import com.rogrand.demo.http.subscriber.HttpSubscriber;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.tv_cat_eye_login_register)
    TextView mTvRegister;
    @BindView(R.id.tab_cat_eye_login)
    TabLayout mTabLayout;
    @BindView(R.id.vp_cat_eye_login)
    ViewPager mViewPager;
    @BindView(R.id.vg_cat_eye_login_other_logins)
    ViewGroup mVgOtherLogins;
    @BindView(R.id.vg_cat_eye_login_other_logins_drawer)
    ViewGroup mVgOtherLoginsDrawer;
    @BindView(R.id.iv_cat_eye_login_switch)
    ImageView mIvSwitch;
    @BindView(R.id.vg_cat_eye_login_other_logins_inner)
    ViewGroup mVgOtherLoginsInner;
    @BindView(R.id.vg_cat_eye_login_sina)
    ViewGroup mVgSina;
    @BindView(R.id.vg_cat_eye_login_wechat)
    ViewGroup mVgWechat;
    @BindView(R.id.vg_cat_eye_login_qq)
    ViewGroup mVgQQ;
    @BindView(R.id.vg_cat_eye_login_baidu)
    ViewGroup mVgBaidu;

    @Override
    protected void initInject() {
        mPresenter = new LoginPresenter();
//        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        getSupportActionBar().hide();
        mViewPager.setAdapter(new CatEyeLoginPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onRegisterClick();
            }
        });
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
        mIvSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onDrawerSwitchClick(isDrawerOpen);
            }
        });
        mVgSina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onSinaLoginClick();
            }
        });
        mVgWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onWechatLoginClick();
            }
        });
        mVgQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onQQLoginClick();
            }
        });
        mVgBaidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onBaiduLoginCLick();
            }
        });

    }

    @Override
    public void loginSuccess(int authState) {

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
        objectAnimator.setTarget(mVgOtherLoginsDrawer);
        objectAnimator.start();
    }

    @Override
    public void closeOtherLoginsDrawer() {
        isDrawerOpen = false;
        if (transY == 0)
            transY = mVgOtherLoginsInner.getHeight();
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setPropertyName("translationY");
        objectAnimator.setDuration(200);
        objectAnimator.setFloatValues(0, transY);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setTarget(mVgOtherLoginsDrawer);
        objectAnimator.start();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void showError(String msg) {

    }

}

